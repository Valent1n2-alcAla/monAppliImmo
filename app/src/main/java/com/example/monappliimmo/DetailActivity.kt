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

        // 1. Setup de la Toolbar (importante pour la flèche de retour)
        val toolbar = findViewById<Toolbar>(R.id.detailToolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false) // On cache le titre pour laisser place au design
        }

        // 2. Initialisation des composants (ID vérifiés selon ton XML)
        val tvTitre = findViewById<TextView>(R.id.detailTitre)
        val tvAdresse = findViewById<TextView>(R.id.detailAdresse)
        val tvPrix = findViewById<TextView>(R.id.detailPrix)
        val tvDesc = findViewById<TextView>(R.id.detailDescription)
        val tvProprio = findViewById<TextView>(R.id.detailProprietaire)

        val chipSurface = findViewById<Chip>(R.id.chipSurface)
        val chipPieces = findViewById<Chip>(R.id.chipPieces)

        // 3. Récupération des données passées par l'Intent
        val numero = intent.getStringExtra("NUMERO") ?: "N/A"
        val desc = intent.getStringExtra("DESC") ?: "Aucune description disponible."
        val surface = intent.getDoubleExtra("SURFACE", 0.0)
        val pieces = intent.getIntExtra("PIECES", 0)
        val prix = intent.getDoubleExtra("PRIX", 0.0)
        val adresse = intent.getStringExtra("ADRESSE") ?: "Adresse non renseignée"
        val proprio = intent.getStringExtra("PROPRIO") ?: "N/A"

        // 4. Injection des données dans les vues
        tvTitre.text = "Appartement $numero"
        tvAdresse.text = adresse
        tvPrix.text = String.format(Locale.FRANCE, "%,.0f €", prix)
        tvDesc.text = desc
        tvProprio.text = "Locataire : $proprio"

        chipSurface.text = "$surface m²"
        chipPieces.text = "$pieces pièces"
    }

    // Gestion de la flèche de retour
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed() // Utilise la nouvelle méthode recommandée
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}