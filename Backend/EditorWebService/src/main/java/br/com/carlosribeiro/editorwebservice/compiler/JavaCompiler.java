/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.compiler;

import br.com.carlosribeiro.editorwebservice.util.FileUtil;
import java.io.IOException;

/**
 *
 * @author carlos.ribeiro
 */
public final class JavaCompiler extends Compiler {

    public JavaCompiler() {
        this.comando = "javac";
        this.extensao = "java";

    }

    @Override
    public String submit(String texto, String linguagem) {

        try {
            StringBuilder output = new StringBuilder();

            FileUtil.salvarArquivoTexto("Main", texto, extensao);
            this.arquivo = "Main";

            output.append(compile());
            output.append(run());
            
            return output.toString();
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex.getMessage());
            return ex.getMessage();
        }

    }

    @Override
    String compile() throws IOException, InterruptedException {

        try {
            return runProcess(this.comando, this.arquivo + ".java");
        } catch (IOException | InterruptedException ex) {
            throw ex;
        }
    }

    @Override
    String run() throws IOException, InterruptedException{
        try {
            return runProcess("java", this.arquivo);
        } catch (IOException | InterruptedException ex) {
            throw ex;
        }
    }

}
