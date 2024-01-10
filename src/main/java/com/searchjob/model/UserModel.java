package com.searchjob.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private String tipo;
    @OneToMany(mappedBy = "anunciante", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("vagaAnunciada")
    private List<VagaModel> vagaAnunciada;
    @ManyToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("vagaParticipando")
    private List<VagaModel> vagaParticipando;
    @OneToOne
    @JsonIgnoreProperties("userModel")
    private PerfilModel perfil;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<VagaModel> getVagaAnunciada() {
        return vagaAnunciada;
    }

    public void setVagaAnunciada(List<VagaModel> vagaAnunciada) {
        this.vagaAnunciada = vagaAnunciada;
    }

    public List<VagaModel> getVagaParticipando() {
        return vagaParticipando;
    }

    public void setVagaParticipando(List<VagaModel> vagaParticipando) {
        this.vagaParticipando = vagaParticipando;
    }

    public PerfilModel getPerfil() {
        return perfil;
    }
    public void setPerfil(PerfilModel perfil) {
        this.perfil = perfil;
    }
}
