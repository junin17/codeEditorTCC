/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carlosribeiro.editorwebservice.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlos.ribeiro
 */
@Entity
@Table(name = "problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Problema.findAll", query = "SELECT p FROM Problema p")
    , @NamedQuery(name = "Problema.findById", query = "SELECT p FROM Problema p WHERE p.id = :id")
    , @NamedQuery(name = "Problema.findByTitulo", query = "SELECT p FROM Problema p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Problema.findByDescricao", query = "SELECT p FROM Problema p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Problema.findByNumero", query = "SELECT p FROM Problema p WHERE p.numero = :numero")
    , @NamedQuery(name = "Problema.findByPontuacao", query = "SELECT p FROM Problema p WHERE p.pontuacao = :pontuacao")})
public class Problema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "pontuacao")
    private Integer pontuacao;
    @OneToMany(mappedBy = "problema", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<CasosTestes> casosTestes;

    public Problema() {
    }

    public Problema(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    @XmlTransient
    public List<CasosTestes> getCasosTestes() {
        return casosTestes;
    }

    public void setCasosTestes(List<CasosTestes> casosTestes) {
        this.casosTestes = casosTestes;
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
        if (!(object instanceof Problema)) {
            return false;
        }
        Problema other = (Problema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.carlosribeiro.editorwebservice.bean.Problema[ id=" + id + " ]";
    }
    
}
