/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlos.ribeiro
 */
@Entity
@Table(name = "casos_testes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasosTestes.findAll", query = "SELECT c FROM CasosTestes c")
    , @NamedQuery(name = "CasosTestes.findById", query = "SELECT c FROM CasosTestes c WHERE c.id = :id")
    , @NamedQuery(name = "CasosTestes.findByEntrada", query = "SELECT c FROM CasosTestes c WHERE c.entrada = :entrada")
    , @NamedQuery(name = "CasosTestes.findBySaida", query = "SELECT c FROM CasosTestes c WHERE c.saida = :saida")})
public class CasosTestes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "entrada")
    private String entrada;
    @Size(max = 2147483647)
    @Column(name = "saida")
    private String saida;
    @JoinColumn(name = "problema_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Problema problema;

    public CasosTestes() {
    }

    public CasosTestes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasosTestes)) {
            return false;
        }
        CasosTestes other = (CasosTestes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.carlosribeiro.editorwebservice.bean.CasosTestes[ id=" + id + " ]";
    }
    
}
