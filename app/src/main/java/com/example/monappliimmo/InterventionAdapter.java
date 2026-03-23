package com.example.monappliimmo;

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

    /**
     * Convertit une date du format "yyyy-MM-dd" vers "dd/MM/yyyy"
     */
    private String formatteDateEurope(String dateBrute) {
        try {
            // 1. On définit le format source (celui de la base de données/API)
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            // 2. On définit le format de destination (Européen)
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

            // 3. On fait la conversion
            Date date = sdfSource.parse(dateBrute);
            return sdfDestination.format(date);
        } catch (ParseException e) {
            // Si le format reçu n'est pas celui attendu, on renvoie la chaîne brute pour ne pas perdre l'info
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

        // Affichage de la date au format européen
        if (inter.getDatePrevue() != null && !inter.getDatePrevue().isEmpty()) {
            // ON APPELLE LE FORMATTAGE ICI
            String dateEuropeenne = formatteDateEurope(inter.getDatePrevue());
            holder.tvDate.setText(dateEuropeenne);
        } else {
            holder.tvDate.setText("En attente");
        }
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