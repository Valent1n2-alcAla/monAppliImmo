package com.example.monappliimmo;

import java.io.Serializable;

public class Appartement implements Serializable {
    private long id;
    private int numero;
    private String description;
    private double surface;
    private int nombrePieces;

    // --- NOUVEAUX CHAMPS (Jointure et Data) ---
    private double prix;
    private String proprietaire;
    private Batiment batiment; // L'objet bâtiment qui contient l'adresse

    public Appartement() {
    }

    // --- GETTERS & SETTERS ---

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getSurface() { return surface; }
    public void setSurface(double surface) { this.surface = surface; }

    public int getNombrePieces() { return nombrePieces; }
    public void setNombrePieces(int nombrePieces) { this.nombrePieces = nombrePieces; }

    // Nouveaux Getters/Setters pour l'API
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getProprietaire() { return proprietaire; }
    public void setProprietaire(String proprietaire) { this.proprietaire = proprietaire; }

    public Batiment getBatiment() { return batiment; }
    public void setBatiment(Batiment batiment) { this.batiment = batiment; }
}