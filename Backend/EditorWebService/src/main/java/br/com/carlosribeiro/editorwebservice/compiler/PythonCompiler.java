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
public class PythonCompiler extends Compiler {

    public PythonCompiler() {
        this.comando = "Python";
        this.extensao = ".py";

    }

    @Override
    public Resposta submit(String texto, String linguagem) {
        Resposta resposta = new Resposta();
        try {
            FileUtil.salvaArquivoTexto("Main", texto, extensao);
            this.arquivo = "Main";
            
            
            
            Execucao run = runProcess(this.comando, this.arquivo.concat(this.extensao));;
            
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
