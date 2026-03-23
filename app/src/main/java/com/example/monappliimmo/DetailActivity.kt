package com.example.monappliimmo

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.Chip
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. Configuration de la Toolbar (Flèche de retour)
        val toolbar = findViewById<Toolbar?>(R.id.detailToolbar)
        setSupportActionBar(toolbar)

        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        }

        // 2. Initialisation de TOUTES les vues (doivent être dans activity_detail.xml)
        val tvTitre = findViewById<TextView>(R.id.detailTitre)
        val tvAdresse = findViewById<TextView>(R.id.detailAdresse) // Ajouté
        val tvPrix = findViewById<TextView>(R.id.detailPrix) // Ajouté
        val tvDesc = findViewById<TextView>(R.id.detailDescription)
        val tvProprio = findViewById<TextView>(R.id.detailProprietaire) // Ajouté

        val chipSurface = findViewById<Chip>(R.id.chipSurface)
        val chipPieces = findViewById<Chip>(R.id.chipPieces)

        // 3. Récupération des données envoyées par l'Adapter
        val numero = getIntent().getStringExtra("NUMERO")
        val desc = getIntent().getStringExtra("DESC")
        val surface = getIntent().getDoubleExtra("SURFACE", 0.0)
        val pieces = getIntent().getIntExtra("PIECES", 0)

        // Nouvelles données
        val prix = getIntent().getDoubleExtra("PRIX", 0.0)
        val adresse = getIntent().getStringExtra("ADRESSE")
        val proprio = getIntent().getStringExtra("PROPRIO")

        // 4. Affichage dynamique des données
        tvTitre.setText("Appartement " + (if (numero != null) numero else "N/A"))
        tvAdresse.setText(if (adresse != null) adresse else "Adresse non renseignée")

        // Formatage du prix (ex: 250 000 €)
        tvPrix.setText(String.format(Locale.FRANCE, "%,.0f €", prix))

        if (desc != null && !desc.isEmpty()) {
            tvDesc.setText(desc)
        } else {
            tvDesc.setText("Aucune description disponible pour ce bien.")
        }

        tvProprio.setText("Propriétaire : " + (if (proprio != null) proprio else "N/A"))

        // Remplissage des Chips
        chipSurface.setText(surface.toString() + " m²")
        chipPieces.setText(pieces.toString() + " pièces")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish() // Ferme l'activité et revient à la liste
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}