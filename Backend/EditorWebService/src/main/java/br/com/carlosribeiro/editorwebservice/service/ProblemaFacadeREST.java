/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.service;

import br.com.carlosribeiro.editorwebservice.bean.CasosTestes;
import br.com.carlosribeiro.editorwebservice.bean.Problema;
import br.com.carlosribeiro.editorwebservice.enums.RespostaEnum;
import br.com.carlosribeiro.editorwebservice.model.Resposta;
import br.com.carlosribeiro.editorwebservice.util.RestUtil;
import com.google.gson.Gson;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author carlos.ribeiro
 */
@Stateless
@Path("problemaCadastro")
public class ProblemaFacadeREST extends AbstractFacade<Problema> {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;
    private final Gson gson;

    public ProblemaFacadeREST() {
        super(Problema.class);
        gson = new Gson();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Problema entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Problema entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Problema find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Problema> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Problema> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @POST
    @Path("salvar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvarProblema(String content) {
        Resposta resposta = new Resposta();
        try{
            
            
            Problema problema = gson.fromJson(content, Problema.class);
            
            if (problema.getCasosTestes() != null){
                for (CasosTestes caso : problema.getCasosTestes()){
                    caso.setProblema(problema);
                }
            }
            
            super.create(problema);
            resposta.setSaida("Problema Salvo com Sucesso!");
            resposta.setStatus(RespostaEnum.OK.getValor());
            return RestUtil.responseOk(gson.toJson(resposta));
        }
        catch(Exception ex){
            System.out.println(ex);
            resposta.setSaida("Erro ao Salvar Problema!");
            resposta.setStatus(RespostaEnum.ERROR.getValor());
            return RestUtil.responseOk(gson.toJson(resposta));
        }
        
        
    }
    
}
