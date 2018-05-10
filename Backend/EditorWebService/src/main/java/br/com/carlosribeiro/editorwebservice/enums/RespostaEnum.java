/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.enums;

/**
 *
 * @author carlos.ribeiro
 */
public enum RespostaEnum {
    OK(0),
    ERROR(1);

    private final int valor;

    public int getValor() {
        return valor;
    }
    
    
    
    RespostaEnum(int valor) {
        this.valor = valor;
    }
}
