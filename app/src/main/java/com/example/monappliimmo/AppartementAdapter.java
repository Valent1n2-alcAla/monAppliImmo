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

    public AppartementAdapter(List<Appartement> listeAppartements) {
        this.listeAppartements = listeAppartements;
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

        // Affichage dans la liste principale
        holder.tvNumero.setText("Appartement n°" + appartement.getNumero());
        holder.tvDescription.setText(appartement.getDescription());
        holder.tvSurface.setText(appartement.getSurface() + " m² - " + appartement.getNombrePieces() + " pièces");

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);

            // 1. Données de l'objet Appartement
            intent.putExtra("ID", appartement.getId());
            intent.putExtra("NUMERO", String.valueOf(appartement.getNumero()));
            intent.putExtra("DESC", appartement.getDescription());
            intent.putExtra("SURFACE", appartement.getSurface());
            intent.putExtra("PIECES", appartement.getNombrePieces());

            // 2. RÉCUPÉRATION DU LOYER ET DU LOCATAIRE (Via le Contrat)
            // Dans ton SQL, ces infos sont dans la table 'contrat'
            if (appartement.getContrats() != null && !appartement.getContrats().isEmpty()) {
                // On récupère le premier contrat (le plus récent)
                Contrat contratActuel = appartement.getContrats().get(0);

                double totalLoyer = contratActuel.getMontantBrut() + contratActuel.getMontantCharge();
                intent.putExtra("PRIX", totalLoyer);

                if (contratActuel.getLocataire() != null) {
                    String nomComplet = contratActuel.getLocataire().getPrenom() + " " + contratActuel.getLocataire().getNom();
                    intent.putExtra("PROPRIO", nomComplet); // Ici on affiche le locataire actuel
                }
            } else {
                intent.putExtra("PRIX", 0.0);
                intent.putExtra("PROPRIO", "Logement vacant");
            }

            // 3. RÉCUPÉRATION DE L'ADRESSE (Via la jointure Batiment)
            if (appartement.getBatiment() != null) {
                String adresseComplete = appartement.getBatiment().getAdresse() + ", " + appartement.getBatiment().getVille();
                intent.putExtra("ADRESSE", adresseComplete);
            } else {
                intent.putExtra("ADRESSE", "Adresse non renseignée");
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