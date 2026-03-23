package com.example.monappliimmo;

import java.io.Serializable;
import java.util.List;

public class Batiment implements Serializable {
    private long id;
    private String adresse;
    private String ville;
    private List<Appartement> appartements;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public List<Appartement> getAppartements() { return appartements; }
    public void setAppartements(List<Appartement> appartements) { this.appartements = appartements; }
}