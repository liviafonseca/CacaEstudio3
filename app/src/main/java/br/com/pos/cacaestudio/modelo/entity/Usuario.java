package br.com.pos.cacaestudio.modelo.entity;

import android.content.Context;

import java.io.Serializable;

import br.com.pos.cacaestudio.activity.LoginActivity;
import br.com.pos.cacaestudio.modelo.dao.UsuarioDAO;

/**
 * Created by livia on 14/03/2018.
 */

public class Usuario implements Serializable {

    private int id;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private String confSenha;

    public Usuario(){}

    public Usuario(int id, String nome, String senha, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setConfSenha(String confSenha) { this.confSenha = confSenha;
    }
}
