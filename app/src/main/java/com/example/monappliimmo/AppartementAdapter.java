package com.example.monappliimmo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppartementAdapter extends RecyclerView.Adapter<AppartementAdapter.AppartementViewHolder> {

    private List<Appartement> listeAppartements;
    private Batiment batimentParent;

    // CONSTRUCTEUR 1 : Pour le BatimentDetailActivity (avec parent)
    public AppartementAdapter(List<Appartement> listeAppartements, Batiment batimentParent) {
        this.listeAppartements = listeAppartements;
        this.batimentParent = batimentParent;
    }

    // CONSTRUCTEUR 2 : Pour le HomeFragment (sans parent)
    // Cela évite l'erreur "actual and formal argument lists differ in length"
    public AppartementAdapter(List<Appartement> listeAppartements) {
        this.listeAppartements = listeAppartements;
        this.batimentParent = null;
    }

    @NonNull
    @Override
    public AppartementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appartement, parent, false);
        return new AppartementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppartementViewHolder holder, int position) {
        Appartement appartement = listeAppartements.get(position);

        // --- AFFICHAGE DANS LA LISTE ---
        holder.tvNumero.setText("Appartement n°" + appartement.getNumero());
        holder.tvDescription.setText(appartement.getDescription());
        holder.tvSurface.setText(appartement.getSurface() + " m² - " + appartement.getNombrePieces() + " pièces");

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);

            // 1. Données de l'appartement
            intent.putExtra("ID", appartement.getId());
            intent.putExtra("NUMERO", String.valueOf(appartement.getNumero()));
            intent.putExtra("DESC", appartement.getDescription());
            intent.putExtra("SURFACE", appartement.getSurface());
            intent.putExtra("PIECES", appartement.getNombrePieces());

            // 2. RÉCUPÉRATION DE L'ADRESSE
            if (batimentParent != null) {
                // Si on vient du détail d'un bâtiment
                String adresseComplete = batimentParent.getAdresse() + ", " + batimentParent.getVille();
                intent.putExtra("ADRESSE", adresseComplete);
            } else if (appartement.getBatiment() != null) {
                // Si on vient de l'accueil (HomeFragment)
                String adresseComplete = appartement.getBatiment().getAdresse() + ", " + appartement.getBatiment().getVille();
                intent.putExtra("ADRESSE", adresseComplete);
            } else {
                intent.putExtra("ADRESSE", "Adresse non renseignée");
            }

            // 3. LOYER ET LOCATAIRE (Via le Contrat)
            if (appartement.getContrats() != null && !appartement.getContrats().isEmpty()) {
                Contrat contratActuel = appartement.getContrats().get(0);
                double totalLoyer = contratActuel.getMontantBrut() + contratActuel.getMontantCharge();
                intent.putExtra("PRIX", totalLoyer);

                if (contratActuel.getLocataire() != null) {
                    String nomComplet = contratActuel.getLocataire().getPrenom() + " " + contratActuel.getLocataire().getNom();
                    intent.putExtra("PROPRIO", nomComplet);
                }
            } else {
                intent.putExtra("PRIX", 0.0);
                intent.putExtra("PROPRIO", "Logement vacant");
            }

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (listeAppartements != null) ? listeAppartements.size() : 0;
    }

    public static class AppartementViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumero, tvDescription, tvSurface;

        public AppartementViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.textNumero);
            tvDescription = itemView.findViewById(R.id.textDescription);
            tvSurface = itemView.findViewById(R.id.textSurface);
        }
    }
}