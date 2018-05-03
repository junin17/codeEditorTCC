/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author carlos.ribeiro
 */
public class FileUtil {

    /**
     * Método para salvar um arquivo texto.
     *
     * @param nomeArq
     * @param texto
     * @param extensao
     */
    public static void salvaArquivoTexto(String nomeArq, String texto, String extensao) {
        try {
            StringBuilder builder = new StringBuilder(nomeArq);
            builder.append(extensao);

            Path file = Paths.get(builder.toString());

            Files.write(file, Arrays.asList(texto), Charset.forName("UTF-8"));
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }

    }
    
    public static List<String> leArquivoTexto(String nomeArq) throws IOException{
        try{
            Path file = Paths.get(nomeArq);
            return Files.readAllLines(file,Charset.forName("UTF-8"));
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
            throw ex;
        }
    }
}
