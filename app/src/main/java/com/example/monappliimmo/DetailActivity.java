package com.example.monappliimmo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.chip.Chip;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 1. Configuration de la Toolbar (Flèche de retour)
        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 2. Initialisation de TOUTES les vues (doivent être dans activity_detail.xml)
        TextView tvTitre = findViewById(R.id.detailTitre);
        TextView tvAdresse = findViewById(R.id.detailAdresse); // Ajouté
        TextView tvPrix = findViewById(R.id.detailPrix);       // Ajouté
        TextView tvDesc = findViewById(R.id.detailDescription);
        TextView tvProprio = findViewById(R.id.detailProprietaire); // Ajouté

        Chip chipSurface = findViewById(R.id.chipSurface);
        Chip chipPieces = findViewById(R.id.chipPieces);

        // 3. Récupération des données envoyées par l'Adapter
        String numero = getIntent().getStringExtra("NUMERO");
        String desc = getIntent().getStringExtra("DESC");
        double surface = getIntent().getDoubleExtra("SURFACE", 0.0);
        int pieces = getIntent().getIntExtra("PIECES", 0);

        // Nouvelles données
        double prix = getIntent().getDoubleExtra("PRIX", 0.0);
        String adresse = getIntent().getStringExtra("ADRESSE");
        String proprio = getIntent().getStringExtra("PROPRIO");

        // 4. Affichage dynamique des données
        tvTitre.setText("Appartement " + (numero != null ? numero : "N/A"));
        tvAdresse.setText(adresse != null ? adresse : "Adresse non renseignée");

        // Formatage du prix (ex: 250 000 €)
        tvPrix.setText(String.format(Locale.FRANCE, "%,.0f €", prix));

        if (desc != null && !desc.isEmpty()) {
            tvDesc.setText(desc);
        } else {
            tvDesc.setText("Aucune description disponible pour ce bien.");
        }

        tvProprio.setText("Propriétaire : " + (proprio != null ? proprio : "N/A"));

        // Remplissage des Chips
        chipSurface.setText(surface + " m²");
        chipPieces.setText(pieces + " pièces");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Ferme l'activité et revient à la liste
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}