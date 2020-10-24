package com.backend.backend.repositorys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.io.Serializable;

@Entity
public class Ubicacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private int grupo;
    @Column
    private int apartamento;
    @OneToOne
    private Users user;
    @Column
    private String seach;

    public Ubicacion() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getApartamento() {
        return apartamento;
    }

    public void setApartamento(int apartamento) {
        this.apartamento = apartamento;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getSeach() {
        return seach;
    }

    public void setSeach(String seach) {
        this.seach = seach;
    }

    public void addOrUpdateSerch() {
        seach = "" + grupo + " " + apartamento + " " + user.getName() + " " + user.getSolapin();
    }

    @Override
    public String toString() {
        return "Ubicacion id:" + id;
    }

    private static final long serialVersionUID = 1L;
}