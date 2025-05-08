/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

/**
 *
 * @author Alec
 */
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = """
            Robot r1
            r1.iniciar
            r1.base=90
            r1.cuerpo=45
            r1.garra=100
            r1.velocidad=2
            r1.finalizar
        """;

        Reader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token token;
        while ((token = lexer.yylex())!= null) {
            System.out.println(token);
        }
    }
}
