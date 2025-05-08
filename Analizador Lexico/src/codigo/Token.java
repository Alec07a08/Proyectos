/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

/**
 *
 * @author Alec
 */

public class Token {
    public String tipo;
    public String valor;
    public String parametros;

    public Token(String tipo, String valor, String parametros) {
        this.tipo = tipo;
        this.valor = valor;
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        return String.format("TOKEN: %-10s | TIPO: %-10s | VALOR: %-10s | PARAMETROS: %s", valor, tipo, valor, parametros);
    }
}

