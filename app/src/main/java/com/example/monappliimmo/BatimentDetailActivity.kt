package com.example.monappliimmo

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BatimentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batiment_detail)

        // 1. Configuration de la Toolbar avec flèche de retour
        val toolbar = findViewById<Toolbar?>(R.id.toolbar_batiment)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.title = "Détails de l'immeuble"
        }

        // 2. Récupération du bâtiment
        val batiment = intent.getSerializableExtra("BATIMENT_OBJ") as Batiment?

        if (batiment != null) {
            val tvAdresse = findViewById<TextView>(R.id.tvDetailAdresse)
            val tvVille = findViewById<TextView>(R.id.tvDetailVille)

            tvAdresse.text = batiment.adresse
            tvVille.text = batiment.ville

            // 3. Configuration de la liste des appartements
            val rv = findViewById<RecyclerView>(R.id.rvAppartsDuBatiment)
            rv.layoutManager = LinearLayoutManager(this)

            if (batiment.appartements != null) {
                // CORRECTION : On utilise .filterNotNull() pour transformer
                // MutableList<Appartement?>? en List<Appartement>
                val listeFiltree = batiment.appartements!!.filterNotNull()

                val adapter = AppartementAdapter(listeFiltree, batiment)
                rv.adapter = adapter
            }
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