/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.util;
import javax.ws.rs.core.Response;

/**
 *
 * @author carlos.ribeiro
 */
public class RestUtil {
    
    public static Response responseOk(String resposta){
        return Response.ok() //200
			.entity(resposta)
			.header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
    }
    
    public static Response responseError(){
        return Response.serverError()
			.header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
    }
}
