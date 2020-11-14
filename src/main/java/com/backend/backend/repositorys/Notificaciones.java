package com.backend.backend.repositorys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.io.Serializable;

@Entity
public class Notificaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String mensaje;
    @Column
    private String nombreRemitente;
    @Column
    private String nombreDestinatario;
    @OneToOne
    private Users remitente;
    @OneToOne
    private Users destinatario;

    public Notificaciones() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Users getRemitente() {
        return remitente;
    }

    public void setRemitente(Users remitente) {
        this.remitente = remitente;
    }

    public Users getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Users destinatario) {
        this.destinatario = destinatario;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    @Override
    public String toString() {
        return "Notificaciones id:" + id;
    }

    private static final long serialVersionUID = 1L;
}