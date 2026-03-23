package com.example.monappliimmo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BatimentAdapter(
    private val listeBatiments: List<Batiment>
) : RecyclerView.Adapter<BatimentAdapter.BatimentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatimentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_batiment, parent, false)
        return BatimentViewHolder(view)
    }

    override fun onBindViewHolder(holder: BatimentViewHolder, position: Int) {
        val batiment = listeBatiments[position]

        holder.tvAdresse.text = batiment.adresse
        holder.tvVille.text = batiment.ville

        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, BatimentDetailActivity::class.java)

            intent.putExtra("BATIMENT_OBJ", batiment)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listeBatiments.size
    }

    class BatimentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAdresse: TextView = itemView.findViewById(R.id.tvAdresseBatiment)
        val tvVille: TextView = itemView.findViewById(R.id.tvVilleBatiment)
    }
}