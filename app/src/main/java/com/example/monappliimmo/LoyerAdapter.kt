package com.example.monappliimmo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class LoyerAdapter(private val loyers: List<Loyer>) : RecyclerView.Adapter<LoyerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.tvDateLoyer)
        val tvMontant: TextView = view.findViewById(R.id.tvMontantLoyer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loyer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loyer = loyers[position]

        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE)

        val dateAffichee = try {
            val date = inputFormat.parse(loyer.datePaiement)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            loyer.datePaiement
        }

        holder.tvDate.text = "Le $dateAffichee"
        holder.tvMontant.text = String.format(Locale.FRANCE, "%,.2f €", loyer.montantPaye)
    }

    override fun getItemCount() = loyers.size
}