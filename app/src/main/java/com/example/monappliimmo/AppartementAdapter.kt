package com.example.monappliimmo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppartementAdapter(
    private val listeAppartements: List<Appartement>?,
    private val batimentParent: Batiment? = null
) : RecyclerView.Adapter<AppartementAdapter.AppartementViewHolder>() {

    constructor(listeAppartements: List<Appartement>?) : this(listeAppartements, null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppartementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appartement, parent, false)
        return AppartementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppartementViewHolder, position: Int) {
        val appartement = listeAppartements?.get(position) ?: return

        holder.tvNumero.text = "Appartement n°${appartement.numero}"
        holder.tvDescription.text = appartement.description
        holder.tvSurface.text = "${appartement.surface} m² - ${appartement.nombrePieces} pièces"

        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra("ID", appartement.id)
            intent.putExtra("NUMERO", appartement.numero.toString())
            intent.putExtra("DESC", appartement.description)
            intent.putExtra("SURFACE", appartement.surface)
            intent.putExtra("PIECES", appartement.nombrePieces)

            val batLocal = appartement.batiment
            val adresseComplete = when {
                batimentParent != null -> "${batimentParent.adresse}, ${batimentParent.ville}"
                batLocal != null -> "${batLocal.adresse}, ${batLocal.ville}"
                else -> "Adresse non renseignée"
            }
            intent.putExtra("ADRESSE", adresseComplete)

            val contratActuel = appartement.contrats?.firstOrNull()

            if (contratActuel != null) {
                val totalLoyer = contratActuel.montantBrut + contratActuel.montantCharge
                intent.putExtra("PRIX", totalLoyer)

                val loc = contratActuel.locataire
                if (loc != null) {
                    val nomComplet = "${loc.prenom} ${loc.nom}"
                    intent.putExtra("PROPRIO", nomComplet)
                    intent.putExtra("LOCATAIRE_ID", loc.id)
                    intent.putExtra("LOCATAIRE_EMAIL", loc.mail)
                } else {
                    intent.putExtra("PROPRIO", "Locataire non renseigné")
                    intent.putExtra("LOCATAIRE_ID", -1L)
                }
            } else {
                intent.putExtra("PRIX", 0.0)
                intent.putExtra("PROPRIO", "Logement vacant")
                intent.putExtra("LOCATAIRE_ID", -1L)

            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listeAppartements?.size ?: 0
    }

    class AppartementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNumero: TextView = itemView.findViewById(R.id.textNumero)
        val tvDescription: TextView = itemView.findViewById(R.id.textDescription)
        val tvSurface: TextView = itemView.findViewById(R.id.textSurface)
    }
}