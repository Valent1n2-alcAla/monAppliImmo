package com.example.monappliimmo;

import java.io.Serializable;

public class Batiment implements Serializable {
    private int id;
    private String nom;
    private String adresse;
    private String ville;

    // Getters
    public String getAdresse() { return adresse; }
    public String getNom() { return nom; }
}