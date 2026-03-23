package com.example.monappliimmo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BatimentAdapter extends RecyclerView.Adapter<BatimentAdapter.BatimentViewHolder> {

    private List<Batiment> listeBatiments;

    public BatimentAdapter(List<Batiment> listeBatiments) {
        this.listeBatiments = listeBatiments;
    }

    @NonNull
    @Override
    public BatimentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_batiment, parent, false);
        return new BatimentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatimentViewHolder holder, int position) {
        Batiment batiment = listeBatiments.get(position);
        holder.tvAdresse.setText(batiment.getAdresse());
        holder.tvVille.setText(batiment.getVille());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BatimentDetailActivity.class);
            intent.putExtra("BATIMENT_OBJ", batiment);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listeBatiments.size();
    }

    public static class BatimentViewHolder extends RecyclerView.ViewHolder {
        TextView tvAdresse, tvVille;

        public BatimentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAdresse = itemView.findViewById(R.id.tvAdresseBatiment);
            tvVille = itemView.findViewById(R.id.tvVilleBatiment);
        }
    }
}