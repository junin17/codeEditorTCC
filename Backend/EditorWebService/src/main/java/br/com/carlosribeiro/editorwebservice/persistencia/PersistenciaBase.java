/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author carlos.ribeiro
 */
public class PersistenciaBase<T> {
    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;
    
    
    public void salvar(T objeto){
        entityManager.persist(objeto);
    }
    
    public void Excluir(T objeto){
        entityManager.remove(objeto);
    }
    
    public void Atualizar(T objeto){
        entityManager.merge(objeto);
    }
}
