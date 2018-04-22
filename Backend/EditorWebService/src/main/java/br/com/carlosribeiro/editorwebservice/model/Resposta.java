/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.model;

/**
 * Classe que contém a resposta de execução do Código.
 * @author carlos.ribeiro
 */
public class Resposta {
    private int status;
    private String saida;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }
    
    
}
