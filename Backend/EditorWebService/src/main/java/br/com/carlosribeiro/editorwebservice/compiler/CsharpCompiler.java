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
 *
 * @author carlos.ribeiro
 */
public class CsharpCompiler extends Compiler{
    
    public CsharpCompiler(){
        this.comando = "C:\\Windows\\Microsoft.NET\\Framework\\v4.0.30319\\csc.exe";
        this.extensao=".cs";
        this.arquivo="Program";
    }

    @Override
    public Resposta submit(String texto, String linguagem) {
        Resposta resposta = new Resposta();
        try {
            FileUtil.salvaArquivoTexto(this.arquivo, texto, extensao);
            
            Execucao compile = runProcess(this.comando, this.arquivo + this.extensao);
            
            if (!StringUtil.isNullOrWhiteSpace(compile.getErroExecucao())){
                resposta.setStatus(RespostaEnum.ERROR.getValor());
                resposta.setSaida(compile.getErroExecucao());
                return resposta;
            }
            
            Execucao run = runProcess("", this.arquivo);
            
            if (!StringUtil.isNullOrWhiteSpace(run.getErroExecucao())){
                resposta.setStatus(RespostaEnum.ERROR.getValor());
                resposta.setSaida(run.getErroExecucao());
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

   
}
