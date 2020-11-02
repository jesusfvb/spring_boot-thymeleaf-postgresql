package com.backend.backend.repositorys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.backend.backend.repositorys.Cuarteleria.Evaluacion;

@Entity
public class Integrante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Evaluacion evaluacion;
    @OneToOne
    private Users integrante;
    @ManyToOne
    private Guardia guardia;
    public Integrante() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Users getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Users integrante) {
        this.integrante = integrante;
    }

    public Guardia getGuardia() {
        return guardia;
    }

    public void setGuardia(Guardia guardia) {
        this.guardia = guardia;
    }
    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
