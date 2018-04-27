/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.compiler;

import br.com.carlosribeiro.editorwebservice.model.Execucao;
import br.com.carlosribeiro.editorwebservice.model.Resposta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Classe abstrata que define um Compilador.
 * @author carlos.ribeiro
 */
public abstract class Compiler {

    protected String extensao;
    protected String comando;
    protected String arquivo;

    abstract Resposta submit(String texto, String linguagem);

    abstract Execucao compile() throws IOException, InterruptedException;

    abstract Execucao run() throws IOException, InterruptedException;

    protected Execucao runProcess(String comando, String arquivo) throws IOException, InterruptedException {
        try {
            Execucao execucao = new Execucao();
            
            String command = comando + " " + arquivo;
            Process pro = Runtime.getRuntime().exec(command);
            
            execucao.setRespostaExecucao(getOutput(pro.getInputStream()));
            execucao.setErroExecucao(getOutput(pro.getErrorStream()));
            
            pro.waitFor();
            
            
            return execucao;
        }
        catch(IOException | InterruptedException ex){
            System.err.println(ex.getMessage());
            throw ex;
        }

    }
    
    private String getOutput(InputStream ins) throws IOException {
        StringBuilder output = new StringBuilder();
        String line;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            output.append(line).append('\n');
        }
        
        return output.toString();
    }

}
