/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analizadorlexicocodigo;

/**
 *
 * @author Alec
 */
import java.util.*;
import java.util.regex.*;

public class Analizador {
    private static final List<ReglaLexica> reglas = new ArrayList<>();

    public static void main(String[] args) {
        inicializarReglas(); 

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Analizador Léxico para Control de Robot ===");
        System.out.println("Escribe instrucciones línea por línea y presiona Enter sobre una línea vacía para analizarlas.\n");

        while (true) {
            StringBuilder bloque = new StringBuilder();
            String linea;

            while (true) {
                linea = scanner.nextLine();
                if (linea.trim().isEmpty()) break;
                bloque.append(linea).append("\n");
            }

            String[] instrucciones = bloque.toString().split("\\n");

            for (String instruccion : instrucciones) {
                if (!instruccion.trim().isEmpty()) {
                    analizarInstruccion(instruccion.trim());
                }
            }

            System.out.println("¿Deseas ingresar más instrucciones? (Escribe 'salir' para terminar o presiona Enter para continuar)");
            String respuesta = scanner.nextLine().trim();
            if (respuesta.equalsIgnoreCase("salir")) {
                System.out.println("Finalizando análisis léxico...");
                break;
            }
        }

        scanner.close();
    }

    public static void analizarInstruccion(String instruccion) {
        System.out.println(">> Analizando: " + instruccion);

        int pos = 0;
        while (pos < instruccion.length()) {
            boolean matchFound = false;

            for (ReglaLexica regla : reglas) {
                Matcher matcher = regla.pattern.matcher(instruccion);
                matcher.region(pos, instruccion.length());
                if (matcher.lookingAt()) {
                    String token = matcher.group();
                    if (!regla.tipo.equals("ESPACIO")) {
                        System.out.printf(">> TOKEN: %-15s → %s%n", token, regla.tipo);
                    }
                    pos = matcher.end();
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                System.out.printf(">> ERROR: Símbolo no reconocido en '%s'%n", instruccion.substring(pos));
                break;
            }
        }

        System.out.println();
    }

    public static class ReglaLexica {
        public Pattern pattern;
        public String tipo;

        public ReglaLexica(String regex, String tipo) {
            this.pattern = Pattern.compile(regex);
            this.tipo = tipo;
        }
    }

    public static void inicializarReglas() {
        reglas.add(new ReglaLexica("//.*", "COMENTARIO"));
        reglas.add(new ReglaLexica("\\b(Robot)\\b", "OBJETO_ROBOT"));
        reglas.add(new ReglaLexica("\\b(iniciar|detener|cerrarGarra|abrirGarra|repetir|finalizar)\\b", "ACCION"));
        reglas.add(new ReglaLexica("\\b(b1|r1)\\b", "IDENTIFICADOR"));
        reglas.add(new ReglaLexica("\\b(base|cuerpo|garra|velocidad)\\b", "METODO"));
        reglas.add(new ReglaLexica("\\d+", "NUMERO_ENTERO"));
        reglas.add(new ReglaLexica("\\.", "OPERADOR_PUNTO"));
        reglas.add(new ReglaLexica("=", "OPERADOR_ASIGNACION"));
        reglas.add(new ReglaLexica("[(),;]", "DELIMITADOR"));
        reglas.add(new ReglaLexica("\\s+", "ESPACIO")); // ignoramos visualmente pero se reconoce
    }
}

