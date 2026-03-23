package com.example.monappliimmo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InterventionAdapter extends RecyclerView.Adapter<InterventionAdapter.InterventionViewHolder> {

    private List<Intervention> interventions;

    public InterventionAdapter(List<Intervention> interventions) {
        this.interventions = interventions;
    }

    private String formatteDateEurope(String dateBrute) {
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            Date date = sdfSource.parse(dateBrute);
            return sdfDestination.format(date);
        } catch (ParseException e) {
            return dateBrute;
        }
    }

    @NonNull
    @Override
    public InterventionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intervention, parent, false);
        return new InterventionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterventionViewHolder holder, int position) {
        Intervention inter = interventions.get(position);

        holder.tvType.setText(inter.getType());
        holder.tvDesc.setText(inter.getDescription());

        // Formatage de la date pour l'affichage dans la liste
        if (inter.getDatePrevue() != null && !inter.getDatePrevue().isEmpty()) {
            holder.tvDate.setText(formatteDateEurope(inter.getDatePrevue()));
        } else {
            holder.tvDate.setText("En attente");
        }

        // --- GESTION DU CLIC POUR LES DÉTAILS ---
        holder.itemView.setOnClickListener(v -> {
            // On crée l'intention de passer à l'écran de détail
            Intent intent = new Intent(v.getContext(), InterventionDetailActivity.class);

            // On passe l'objet complet (Assure-toi que ta classe Intervention implémente Serializable !)
            intent.putExtra("intervention_selectionnee", inter);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (interventions != null) ? interventions.size() : 0;
    }

    public static class InterventionViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvDesc, tvDate;

        public InterventionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.textTypeInter);
            tvDesc = itemView.findViewById(R.id.textDescInter);
            tvDate = itemView.findViewById(R.id.textDateInter);
        }
    }
}