package com.example.monappliimmo

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class InterventionDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervention_detail)

        // 1. Récupérer les composants UI
        val tvType = findViewById<TextView>(R.id.detailType)
        val tvDate = findViewById<TextView>(R.id.detailDate)
        val tvDesc = findViewById<TextView>(R.id.detailDesc)
        val tvAppart = findViewById<TextView>(R.id.detailAppartement) // Ajout de l'appartement
        val btnRetour = findViewById<Button>(R.id.btnRetour) // Ajout du bouton

        // 2. Activer la flèche de retour dans la barre du haut
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Détail intervention"
        }

        // 3. Récupérer l'objet Intervention
        val inter = intent.getSerializableExtra("intervention_selectionnee") as? Intervention

        inter?.let {
            tvType.text = it.type
            tvDesc.text = it.description

            // Affichage de l'appartement lié
            tvAppart.text = it.appartement?.numero?.toString() ?: "Appartement non spécifié"

            // Formatage de la date
            tvDate.text = it.datePrevue?.let { date -> formatteDateEurope(date) } ?: "Non planifiée"
        }

        // 4. Action du bouton "Retour à la liste" en bas de l'écran
        btnRetour.setOnClickListener {
            finish() // Ferme l'activité et revient à la liste
        }
    }

    private fun formatteDateEurope(dateBrute: String): String {
        return try {
            val sdfSource = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val sdfDestination = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
            val date = sdfSource.parse(dateBrute)
            sdfDestination.format(date!!)
        } catch (e: Exception) {
            dateBrute
        }
    }

    // Gestion du clic sur la flèche de retour (en haut à gauche)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}