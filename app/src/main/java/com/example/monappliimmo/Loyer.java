package com.example.monappliimmo;

import java.io.Serializable;

public class Loyer implements Serializable {
    private long id;
    private double montantPaye;
    private String datePaiement;
    private Contrat contrat;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public double getMontantPaye() { return montantPaye; }
    public void setMontantPaye(double montantPaye) { this.montantPaye = montantPaye; }
    public String getDatePaiement() { return datePaiement; }
    public void setDatePaiement(String datePaiement) { this.datePaiement = datePaiement; }
    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }
}