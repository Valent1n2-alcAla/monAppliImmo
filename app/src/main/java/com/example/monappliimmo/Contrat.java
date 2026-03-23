package com.example.monappliimmo;

import java.io.Serializable;

public class Contrat implements Serializable {
    private long id;
    private double montantBrut;
    private double montantCharge;
    private String dateDebut;
    private String dateFin;
    private Locataire locataire;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public double getMontantBrut() { return montantBrut; }
    public void setMontantBrut(double montantBrut) { this.montantBrut = montantBrut; }
    public double getMontantCharge() { return montantCharge; }
    public void setMontantCharge(double montantCharge) { this.montantCharge = montantCharge; }
    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }
    public Locataire getLocataire() { return locataire; }
    public void setLocataire(Locataire locataire) { this.locataire = locataire; }
}