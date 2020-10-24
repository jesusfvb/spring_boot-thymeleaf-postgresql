package com.backend.backend.repositorys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.backend.backend.repositorys.Cuarteleria.Evaluacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Guardia implements Serializable {

    public enum Lugar {
        Residencia, Docente
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String fecha;
    @Column
    private String inicio;
    @Column
    private String fin;
    @Column
    private String advertencia;
    @OneToOne
    private Users representante;
    @Column
    private Lugar ubicacion;
    @ManyToMany
    List<Users> integrantes;
    @Column
    ArrayList<Evaluacion> evaluaciones;
    @Column
    private String seach;

    public Guardia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getAdvertencia() {
        return advertencia;
    }

    public void setAdvertencia(String advertencia) {
        this.advertencia = advertencia;
    }

    public Users getRepresentante() {
        return representante;
    }

    public void setRepresentante(Users representante) {
        this.representante = representante;
    }

    public Lugar getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Lugar ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Users> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Users> integrantes) {
        this.integrantes = integrantes;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public String getSeach() {
        return seach;
    }

    public void setSeach(String seach) {
        this.seach = seach;
    }

    public void addOrUpdateSerch() {
        seach = "" + fecha + " " + inicio + " " + fin + " " + representante.getName() + " " + ubicacion;
    }

    @Override
    public String toString() {
        return "Guardia id:" + id;
    }

    private static final long serialVersionUID = 1293056009960585075L;
}