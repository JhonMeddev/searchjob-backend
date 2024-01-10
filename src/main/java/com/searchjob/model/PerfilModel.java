package com.searchjob.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.searchjob.model.utils.AreasEnum;

import javax.persistence.*;


@Entity
@Table(name = "tb_perfil")
public class PerfilModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private AreasEnum area;
    private String descricao;
    @OneToOne
    @JsonIgnoreProperties("perfilModel")
    private UserModel usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AreasEnum getArea() {
        return area;
    }

    public void setArea(AreasEnum area) {
        this.area = area;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }
}
