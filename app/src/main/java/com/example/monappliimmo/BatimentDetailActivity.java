package com.example.monappliimmo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BatimentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batiment_detail);

        // 1. Configuration de la Toolbar avec flèche de retour
        Toolbar toolbar = findViewById(R.id.toolbar_batiment);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Détails de l'immeuble");
        }

        // 2. Récupération du bâtiment
        Batiment batiment = (Batiment) getIntent().getSerializableExtra("BATIMENT_OBJ");

        if (batiment != null) {
            TextView tvAdresse = findViewById(R.id.tvDetailAdresse);
            TextView tvVille = findViewById(R.id.tvDetailVille);

            tvAdresse.setText(batiment.getAdresse());
            tvVille.setText(batiment.getVille());

            // 3. Configuration de la liste des appartements
            RecyclerView rv = findViewById(R.id.rvAppartsDuBatiment);
            rv.setLayoutManager(new LinearLayoutManager(this));

            if (batiment.getAppartements() != null) {
                // IMPORTANT : On passe la liste d'apparts ET l'objet batiment lui-même
                AppartementAdapter adapter = new AppartementAdapter(batiment.getAppartements(), batiment);
                rv.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}