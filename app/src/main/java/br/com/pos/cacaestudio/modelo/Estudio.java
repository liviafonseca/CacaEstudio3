package br.com.pos.cacaestudio.modelo;

/**
 * Created by livia on 17/02/2018.
 */

public class Estudio {

    private int id;
    private String nome;
    private String endereco;
    private String preco;
    private String hora;

    public Estudio(String nome, String endereco, String preco) {
        this.nome = nome;
        this.endereco = endereco;
        this.preco = preco;
    }

    public Estudio(String nome, String hora) {
        this.nome = nome;
        this.hora = hora;
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
