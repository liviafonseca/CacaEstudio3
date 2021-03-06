package br.com.pos.cacaestudio.modelo.entity;

import java.io.Serializable;

/**
 * Created by livia on 17/02/2018.
 */

public class Estudio implements Serializable {

    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private double preco;
    private String img;
    private double media;

    public Estudio() {

    }

    public Estudio(String nome, String hora) {
        this.nome = nome;
        hora = hora;
    }

    public Estudio(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
