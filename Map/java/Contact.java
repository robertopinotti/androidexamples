package com.example.robertopinotti.map;

public class Contact {

    private String nome;
    private double lat;
    private double lon;

    public Contact(String nome, double lat, double lon){
        this.nome=nome;
        this.lat=lat;
        this.lon=lon;
    }

    public String getNome() {return nome;}

    public double getLat() {return lat;}

    public double getLon() {return lon;}
}
