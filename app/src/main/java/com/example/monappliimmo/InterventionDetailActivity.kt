package com.example.monappliimmo

import android.os.Bundle
import android.view.MenuItem
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

        // 2. Activer la flèche de retour (déplacé ici)
        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
            getSupportActionBar()!!.setTitle("Détail intervention")
        }

        // 3. Récupérer l'objet Intervention
        val inter = getIntent().getSerializableExtra("intervention_selectionnee") as Intervention?

        if (inter != null) {
            tvType.setText(inter.type)
            tvDesc.setText(inter.description)

            if (inter.datePrevue != null) {
                tvDate.setText(formatteDateEurope(inter.datePrevue!!))
            } else {
                tvDate.setText("Non planifiée")
            }
        }
    }

    private fun formatteDateEurope(dateBrute: String): String {
        try {
            val sdfSource = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val sdfDestination = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
            val date = sdfSource.parse(dateBrute)
            return sdfDestination.format(date)
        } catch (e: ParseException) {
            return dateBrute
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}