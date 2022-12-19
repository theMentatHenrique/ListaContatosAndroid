package com.example.manipulandopastas.model;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id=0;
    private String nome;
    private String telefone;
    private String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    public Aluno(){


    }

    public boolean temId(){
        return id > 0 ? true : false;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return nome;
    }

    public void setId(int incrementId) {
        this.id=incrementId;
    }
}
