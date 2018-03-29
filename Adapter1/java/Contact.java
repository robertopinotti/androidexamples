package com.example.robertopinotti.adapter1;

public class Contact {

    private String nome = null;
    private String distanza= null;
    private String data = null;

    public Contact(String nome, String distanza, String data) {
        this.nome = nome;
        this.distanza = distanza;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public String getDistanza() {
        return distanza;
    }

    public String getData() {
        return data;
    }

}
