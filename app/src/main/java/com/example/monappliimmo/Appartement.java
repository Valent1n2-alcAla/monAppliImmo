package com.example.monappliimmo;

import java.io.Serializable;
import java.util.List;

public class Appartement implements Serializable {
    private long id;
    private int numero;
    private String description;
    private double surface;
    private int nombrePieces;
    private Batiment batiment;
    private List<Contrat> contrats;
    private List<Intervention> interventions;

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
    public Batiment getBatiment() { return batiment; }
    public void setBatiment(Batiment batiment) { this.batiment = batiment; }
    public List<Contrat> getContrats() { return contrats; }
    public void setContrats(List<Contrat> contrats) { this.contrats = contrats; }
    public List<Intervention> getInterventions() { return interventions; }
    public void setInterventions(List<Intervention> interventions) { this.interventions = interventions; }
}