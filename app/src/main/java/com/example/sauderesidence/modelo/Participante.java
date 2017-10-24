package com.example.sauderesidence.modelo;

/**
 * Created by sauderesidence on 24/10/17.
 */

public class Participante {
    private Long id;
    private String nome;
    private String email;
    private String telefone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
}
