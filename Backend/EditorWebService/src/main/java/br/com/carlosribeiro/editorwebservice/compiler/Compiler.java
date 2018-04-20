/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author carlos.ribeiro
 */
public abstract class Compiler {

    protected String extensao;
    protected String comando;
    protected String arquivo;

    abstract String submit(String texto, String linguagem);

    abstract String compile() throws IOException, InterruptedException;

    abstract String run() throws IOException, InterruptedException;

    protected String runProcess(String comando, String arquivo) throws IOException, InterruptedException {
        try {
            StringBuilder output = new StringBuilder();
            String command = comando + " " + arquivo;
            Process pro = Runtime.getRuntime().exec(command);
            //printLines(command + " stdout:", pro.getInputStream());
            //printLines(command + " stderr:", pro.getErrorStream());
            
            output.append(getOutput(pro.getInputStream()));
            output.append(getOutput(pro.getErrorStream()));
            
            pro.waitFor();
            
            
            return output.toString();
        }
        catch(IOException | InterruptedException ex){
            System.err.println(ex.getMessage());
            throw ex;
        }

    }
    
    private String getOutput(InputStream ins) throws IOException {
        StringBuilder output = new StringBuilder();
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            output.append(line);
        }
        
        return output.toString();
    }

}
