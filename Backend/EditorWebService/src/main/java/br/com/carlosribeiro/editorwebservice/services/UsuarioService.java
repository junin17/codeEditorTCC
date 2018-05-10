/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.services;

import br.com.carlosribeiro.editorwebservice.bean.Usuario;
import br.com.carlosribeiro.editorwebservice.enums.RespostaEnum;
import br.com.carlosribeiro.editorwebservice.model.Resposta;
import br.com.carlosribeiro.editorwebservice.persistencia.PersistenciaBase;
import br.com.carlosribeiro.editorwebservice.util.RestUtil;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author carlos.ribeiro
 */
@Path("usuario")
public class UsuarioService {

    @Context
    private UriInfo context;
    private final Gson gson;

    /**
     * Creates a new instance of UsuarioService
     */
    public UsuarioService() {
        gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of br.com.carlosribeiro.editorwebservice.services.UsuarioService
     * @return an instance of br.com.carlosribeiro.editorwebservice.bean.Usuario
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsuarioService
     * @param content representation for the resource
     * @return 
     */
    @POST
    @Path("cadastro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCadastro(String content) {
        Resposta resposta = new Resposta();
        try{
            
            
            Usuario usuario = gson.fromJson(content, Usuario.class);
            
            (new PersistenciaBase()).salvar(usuario);
            resposta.setSaida("Usuario Salvo com Sucesso!");
            resposta.setStatus(RespostaEnum.OK.getValor());
            return RestUtil.responseOk(gson.toJson(resposta));
        }
        catch(Exception ex){
            System.out.println(ex);
            resposta.setSaida("Erro ao Salvar Usuario!");
            resposta.setStatus(RespostaEnum.ERROR.getValor());
            return RestUtil.responseOk(gson.toJson(resposta));
        }
        
        
    }
}
