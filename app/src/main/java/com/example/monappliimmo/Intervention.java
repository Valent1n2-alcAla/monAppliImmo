package com.example.monappliimmo;

import java.io.Serializable;

public class Intervention implements Serializable {
    private Long id;
    private String type;
    private String description;
    private String datePrevue;
    // Ici, on déclare l'objet Appartement car ton JSON contient un bloc "appartement": {...}
    private Appartement appartement;

    // Getters
    public Long getId() { return id; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getDatePrevue() { return datePrevue; }
    public Appartement getAppartement() { return appartement; }

    // Setters (Optionnels pour Retrofit mais conseillés)
    public void setType(String type) { this.type = type; }
    public void setDescription(String description) { this.description = description; }
    public void setDatePrevue(String datePrevue) { this.datePrevue = datePrevue; }
    public void setAppartement(Appartement appartement) { this.appartement = appartement; }
}