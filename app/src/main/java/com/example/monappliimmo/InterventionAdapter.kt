package com.example.monappliimmo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class InterventionAdapter(
    private val interventions: List<Intervention>
) : RecyclerView.Adapter<InterventionAdapter.InterventionViewHolder>() {

    private fun formatteDateEurope(dateBrute: String): String {
        return try {
            val sdfSource = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val sdfDestination = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
            val date = sdfSource.parse(dateBrute)
            sdfDestination.format(date)
        } catch (e: ParseException) {
            dateBrute
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterventionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_intervention, parent, false)
        return InterventionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InterventionViewHolder, position: Int) {
        val inter = interventions[position]

        holder.tvType.text = inter.type
        holder.tvDesc.text = inter.description

        if (!inter.datePrevue.isNullOrEmpty()) {
            holder.tvDate.text = formatteDateEurope(inter.datePrevue!!)
        } else {
            holder.tvDate.text = "En attente"
        }

        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, InterventionDetailActivity::class.java)

            // ⚠️ Assure-toi que Intervention implémente Serializable
            intent.putExtra("intervention_selectionnee", inter)

            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = interventions.size

    class InterventionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvType: TextView = itemView.findViewById(R.id.textTypeInter)
        val tvDesc: TextView = itemView.findViewById(R.id.textDescInter)
        val tvDate: TextView = itemView.findViewById(R.id.textDateInter)
    }
}