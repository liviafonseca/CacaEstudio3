package br.com.pos.cacaestudio.modelo.entity;

import java.util.Date;

/**
 * Created by livia on 23/03/2018.
 */

public class Agenda {

    private Usuario usuario;
    private Estudio estudio;
    private int id;
    private String data;
    private int hora;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
}
