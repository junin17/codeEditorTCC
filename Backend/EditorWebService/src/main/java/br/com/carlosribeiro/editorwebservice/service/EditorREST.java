/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.service;

import br.com.carlosribeiro.editorwebservice.compiler.CsharpCompiler;
import br.com.carlosribeiro.editorwebservice.compiler.JavaCompiler;
import br.com.carlosribeiro.editorwebservice.compiler.JavaScriptCompiler;
import br.com.carlosribeiro.editorwebservice.compiler.PythonCompiler;
import br.com.carlosribeiro.editorwebservice.enums.RespostaEnum;
import br.com.carlosribeiro.editorwebservice.model.Resposta;
import br.com.carlosribeiro.editorwebservice.model.Submit;
import br.com.carlosribeiro.editorwebservice.util.FileUtil;
import br.com.carlosribeiro.editorwebservice.util.RestUtil;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author carlos.ribeiro
 */
@Path("editor")
public class EditorREST {

    @Context
    private UriInfo context;
    private final Gson gson;

    /**
     * Creates a new instance of EditorService
     */
    public EditorREST() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of
     * br.com.carlosribeiro.editorwebservice.services.EditorService
     *
     * @param linguagem
     * @return an instance of java.lang.String
     */
    @GET
    @Path("padrao/{linguagem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCodigoPadraoJava(@PathParam("linguagem") String linguagem) {
        Resposta resposta = new Resposta();

        try {
            String nomeArquivo = "";

            switch (linguagem) {
                case "java":
                    nomeArquivo = "HelloWorld.java";
                    break;
                case "python":
                    nomeArquivo = "helloworld.py";
                    break;
                case "javascript":
                    nomeArquivo = "helloworld.js";
                    break;
                case "csharp":
                    nomeArquivo = "Program.cs";
                    break;

            }

            StringBuilder codigoFonte = new StringBuilder();
            for (String linha : FileUtil.leArquivoTexto(nomeArquivo)) {
                codigoFonte.append(linha).append("\n");
            }
            resposta.setSaida(codigoFonte.toString());
            resposta.setStatus(RespostaEnum.OK.getValor());

            return RestUtil.responseOk(gson.toJson(resposta));
        } catch (IOException ex) {
            resposta.setSaida(ex.getMessage());
            resposta.setStatus(RespostaEnum.ERROR.getValor());
            return RestUtil.responseOk(gson.toJson(resposta));
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCodigo(String content) {
        Resposta resposta = null;

        try {
            Submit submit = gson.fromJson(content, Submit.class);

            switch (submit.getLinguagem()) {
                case "java":
                    JavaCompiler compiladorJava = new JavaCompiler();
                    resposta = compiladorJava.submit(submit.getCode(), submit.getLinguagem());
                    break;
                case "python":
                    PythonCompiler compiladorPython = new PythonCompiler();
                    resposta = compiladorPython.submit(submit.getCode(), submit.getLinguagem());
                    break;
                case "javascript":
                    JavaScriptCompiler compiladorJS = new JavaScriptCompiler();
                    resposta = compiladorJS.submit(submit.getCode(), submit.getLinguagem());
                    break;
                case "csharp":
                    CsharpCompiler compiladorCSharp = new CsharpCompiler();
                    resposta = compiladorCSharp.submit(submit.getCode(), submit.getLinguagem());
                default:
                    break;
            }
            return RestUtil.responseOk(gson.toJson(resposta));
        }
        catch(Exception ex){
            
           System.err.println(ex);
           return RestUtil.responseError();
        }

        
    }

}
