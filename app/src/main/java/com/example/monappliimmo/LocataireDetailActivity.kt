package com.example.monappliimmo

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.util.*

class LocataireDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locataire_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbarLocataire)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val locataireId = intent.getLongExtra("LOCATAIRE_ID", -1L)
        val nomComplet = intent.getStringExtra("LOCATAIRE_NOM") ?: "Inconnu"
        val email = intent.getStringExtra("LOCATAIRE_EMAIL") ?: "Email non renseigné"

        val tvNom = findViewById<TextView>(R.id.tvNomLocataire)
        val tvEmail = findViewById<TextView>(R.id.tvEmailLocataire)
        val tvInitiales = findViewById<TextView>(R.id.tvInitialesAvatar)
        val rvLoyers = findViewById<RecyclerView>(R.id.rvLoyersLocataire)

        tvNom.text = nomComplet
        tvEmail.text = email
        tvInitiales.text = genererInitiales(nomComplet)

        rvLoyers.layoutManager = LinearLayoutManager(this)

        if (locataireId != -1L) {
            chargerLoyersLocataire(locataireId, rvLoyers)
        } else {
            Toast.makeText(this, "Erreur : ID locataire manquant", Toast.LENGTH_SHORT).show()
        }
    }

    private fun chargerLoyersLocataire(id: Long, rv: RecyclerView) {
        lifecycleScope.launch {
            try {
                val loyers = RetrofitClient.getInstance().api.getLoyersByLocataire(id)
                rv.adapter = LoyerAdapter(loyers)
            } catch (e: Exception) {
                Toast.makeText(this@LocataireDetailActivity, "Erreur réseau", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun genererInitiales(nomComplet: String): String {
        if (nomComplet.isBlank() || nomComplet == "Inconnu") return "?"

        val mots = nomComplet.trim().split("\\s+".toRegex())
        return when {
            mots.size >= 2 -> {
                val p = mots[0].substring(0, 1).uppercase(Locale.FRANCE)
                val n = mots[1].substring(0, 1).uppercase(Locale.FRANCE)
                "$p$n"
            }
            mots.size == 1 -> {
                mots[0].substring(0, 1).uppercase(Locale.FRANCE)
            }
            else -> "?"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}