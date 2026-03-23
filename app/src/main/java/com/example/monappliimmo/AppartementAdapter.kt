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

    // Constructeur alternatif (si besoin)
    constructor(listeAppartements: List<Appartement>?) : this(listeAppartements, null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppartementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appartement, parent, false)
        return AppartementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppartementViewHolder, position: Int) {
        val appartement = listeAppartements?.get(position) ?: return

        // AFFICHAGE DES INFOS DE BASE
        holder.tvNumero.text = "Appartement n°${appartement.numero}"
        holder.tvDescription.text = appartement.description
        holder.tvSurface.text = "${appartement.surface} m² - ${appartement.nombrePieces} pièces"

        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, DetailActivity::class.java)

            // Données appartement
            intent.putExtra("ID", appartement.id)
            intent.putExtra("NUMERO", appartement.numero.toString())
            intent.putExtra("DESC", appartement.description)
            intent.putExtra("SURFACE", appartement.surface)
            intent.putExtra("PIECES", appartement.nombrePieces)

            // Logique pour l'adresse (Capture de batiment pour le Smart Cast)
            val batLocal = appartement.batiment
            val adresseComplete = when {
                batimentParent != null ->
                    "${batimentParent.adresse}, ${batimentParent.ville}"

                batLocal != null ->
                    "${batLocal.adresse}, ${batLocal.ville}"

                else -> "Adresse non renseignée"
            }

            intent.putExtra("ADRESSE", adresseComplete)

            // Gestion du contrat et du locataire (Fix Smart Cast Error)
            val contratActuel = appartement.contrats?.firstOrNull()

            if (contratActuel != null) {
                // On calcule le prix total
                val totalLoyer = contratActuel.montantBrut + contratActuel.montantCharge
                intent.putExtra("PRIX", totalLoyer)

                // Capture du locataire dans une variable locale 'val' pour permettre le Smart Cast
                val loc = contratActuel.locataire
                if (loc != null) {
                    val nomComplet = "${loc.prenom} ${loc.nom}"
                    intent.putExtra("PROPRIO", nomComplet)
                } else {
                    intent.putExtra("PROPRIO", "Locataire non renseigné")
                }
            } else {
                // Cas d'un logement vacant
                intent.putExtra("PRIX", 0.0)
                intent.putExtra("PROPRIO", "Logement vacant")
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