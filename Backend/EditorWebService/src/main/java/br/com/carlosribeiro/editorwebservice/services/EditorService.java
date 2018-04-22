/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.services;

import br.com.carlosribeiro.editorwebservice.compiler.JavaCompiler;
import br.com.carlosribeiro.editorwebservice.model.Resposta;
import br.com.carlosribeiro.editorwebservice.model.Submit;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author carlos.ribeiro
 */
@Path("editor")
public class EditorService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EditorService
     */
    public EditorService() {
    }

    /**
     * Retrieves representation of an instance of br.com.carlosribeiro.editorwebservice.services.EditorService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getText() {
        //TODO return proper representation object
        return Response.ok() //200
			.entity("tamo junto")
			.header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
    }

    /**
     * PUT method for updating or creating an instance of EditorService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCodigo(String content){
        Resposta resposta = null;
        Gson gson = new Gson();
        
        Submit submit = gson.fromJson(content, Submit.class);
        
        if (submit.getLinguagem().equals("java")){
            JavaCompiler compiladorJava = new JavaCompiler();
            resposta = compiladorJava.submit(submit.getCode(), submit.getLinguagem());
            
        }
        
        
        
        return Response.ok() //200
			.entity(gson.toJson(resposta))
			.header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
    }
}
