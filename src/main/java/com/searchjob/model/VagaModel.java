package com.searchjob.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_vaga")
public class VagaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data = new java.sql.Date(System.currentTimeMillis());
    @ManyToOne
    @JsonIgnoreProperties("vagaModel")
    private UserModel anunciante;

    @ManyToMany
    @JsonIgnoreProperties("vagaModel")
    private List<UserModel> candidato;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public UserModel getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(UserModel anunciante) {
        this.anunciante = anunciante;
    }

    public List<UserModel> getCandidato() {
        return candidato;
    }

    public void setCandidato(List<UserModel> candidato) {
        this.candidato = candidato;
    }
}
