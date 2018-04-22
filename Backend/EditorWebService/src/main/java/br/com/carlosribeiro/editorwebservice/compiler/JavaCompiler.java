/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.compiler;

import br.com.carlosribeiro.editorwebservice.enums.RespostaEnum;
import br.com.carlosribeiro.editorwebservice.model.Execucao;
import br.com.carlosribeiro.editorwebservice.model.Resposta;
import br.com.carlosribeiro.editorwebservice.util.FileUtil;
import br.com.carlosribeiro.editorwebservice.util.StringUtil;
import java.io.IOException;

/**
 * Classe para executar os códigos em Java
 * @author carlos.ribeiro
 */
public final class JavaCompiler extends Compiler {

    public JavaCompiler() {
        this.comando = "javac";
        this.extensao = "java";

    }

    @Override
    public Resposta submit(String texto, String linguagem) {
        Resposta resposta = new Resposta();
        try {
            
            StringBuilder output = new StringBuilder();

            FileUtil.salvarArquivoTexto("Main", texto, extensao);
            this.arquivo = "Main";
            
            Execucao compile = compile();
            
            if (!StringUtil.isNullOrWhiteSpace(compile.getErroExecucao())){
                resposta.setStatus(RespostaEnum.ERROR.getValor());
                resposta.setSaida(compile.getErroExecucao());
                return resposta;
            }
            
            Execucao run = run();
            
            if (!StringUtil.isNullOrWhiteSpace(run.getErroExecucao())){
                resposta.setStatus(RespostaEnum.ERROR.getValor());
                resposta.setSaida(run.getRespostaExecucao());
                return resposta;
            }
            
            resposta.setSaida(run.getRespostaExecucao());
            resposta.setStatus(RespostaEnum.OK.getValor());

            
            return resposta;
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex.getMessage());
            resposta.setSaida(ex.getMessage());
            resposta.setStatus(RespostaEnum.ERROR.getValor());
            return resposta;
        }

    }

    @Override
    Execucao compile() throws IOException, InterruptedException {

        try {
            return runProcess(this.comando, this.arquivo + ".java");
        } catch (IOException | InterruptedException ex) {
            throw ex;
        }
    }

    @Override
    Execucao run() throws IOException, InterruptedException{
        try {
            return runProcess("java", this.arquivo);
        } catch (IOException | InterruptedException ex) {
            throw ex;
        }
    }

}
