package com.backend.backend.repositorys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.io.Serializable;

@Entity
public class Cuarteleria implements Serializable {

    public enum Evaluacion {
        Bien, Regular, Mal ,Pendiente
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String fecha;
    @Column
    private Evaluacion evaluacion;
    @OneToOne
    Ubicacion ubicacion;
    @Column
    private String seach;

    public Cuarteleria() {
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

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getSeach() {
        return seach;
    }

    public void setSeach(String seach) {
        this.seach = seach;
    }

    public void addOrUpdateSerch() {
        seach = "" + fecha + " " + evaluacion + " " + ubicacion.getUser().getName() + " " + ubicacion.getUser().getSolapin();
    }

    @Override
    public String toString() {
        return "Cuarteleria id:" + id;
    }

    private static final long serialVersionUID = 1L;
}