package com.example.monappliimmo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.detailToolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val tvTitre = findViewById<TextView>(R.id.detailTitre)
        val tvAdresse = findViewById<TextView>(R.id.detailAdresse)
        val tvPrix = findViewById<TextView>(R.id.detailPrix)
        val tvDesc = findViewById<TextView>(R.id.detailDescription)
        val tvProprio = findViewById<TextView>(R.id.detailProprietaire)
        val chipSurface = findViewById<Chip>(R.id.chipSurface)
        val chipPieces = findViewById<Chip>(R.id.chipPieces)
        val btnHistorique = findViewById<Button>(R.id.btnHistoriqueLoyers)
        val rvLoyers = findViewById<RecyclerView>(R.id.rvLoyers)

        val layoutLocataire = findViewById<LinearLayout>(R.id.layoutClicLocataire)
        val ivFleche = findViewById<ImageView>(R.id.ivFlecheRedirection)

        val appartementId = intent.getLongExtra("ID", 0L)
        val locataireId = intent.getLongExtra("LOCATAIRE_ID", -1L)
        val emailLoc = intent.getStringExtra("LOCATAIRE_EMAIL") ?: "Email non renseigné"
        val numero = intent.getStringExtra("NUMERO") ?: "N/A"
        val desc = intent.getStringExtra("DESC") ?: "Aucune description disponible."
        val surface = intent.getDoubleExtra("SURFACE", 0.0)
        val pieces = intent.getIntExtra("PIECES", 0)
        val prix = intent.getDoubleExtra("PRIX", 0.0)
        val adresse = intent.getStringExtra("ADRESSE") ?: "Adresse non renseignée"
        val proprio = intent.getStringExtra("PROPRIO") ?: "N/A"

        tvTitre.text = "Appartement $numero"
        tvAdresse.text = adresse
        tvPrix.text = String.format(Locale.FRANCE, "%,.0f €", prix)
        tvDesc.text = desc
        tvProprio.text = proprio
        chipSurface.text = "$surface m²"
        chipPieces.text = "$pieces pièces"

        if (locataireId != -1L) {
            ivFleche?.visibility = View.VISIBLE
            layoutLocataire?.setOnClickListener {
                val intentLoc = Intent(this, LocataireDetailActivity::class.java)
                intentLoc.putExtra("LOCATAIRE_ID", locataireId)
                intentLoc.putExtra("LOCATAIRE_NOM", proprio)
                intentLoc.putExtra("LOCATAIRE_EMAIL", emailLoc)
                startActivity(intentLoc)
            }
        } else {
            ivFleche?.visibility = View.GONE
        }

        btnHistorique.setOnClickListener {
            if (rvLoyers.visibility == View.VISIBLE) {
                rvLoyers.visibility = View.GONE
                btnHistorique.text = "Voir l'historique des loyers"
            } else {
                chargerLoyers(appartementId, rvLoyers, btnHistorique)
            }
        }
    }

    private fun chargerLoyers(id: Long, rv: RecyclerView, btn: Button) {
        if (id == 0L) {
            Toast.makeText(this, "Erreur : ID de l'appartement inconnu", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                val listeLoyers = RetrofitClient.getInstance().api.getLoyersByAppartement(id)

                if (listeLoyers.isNotEmpty()) {
                    rv.visibility = View.VISIBLE
                    rv.layoutManager = LinearLayoutManager(this@DetailActivity)
                    rv.adapter = LoyerAdapter(listeLoyers)
                    btn.text = "Cacher l'historique"
                } else {
                    Toast.makeText(this@DetailActivity, "Aucun loyer enregistré", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailActivity, "Erreur réseau : ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}