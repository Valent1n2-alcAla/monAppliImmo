package com.example.monappliimmo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InterventionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervention_detail);

        // 1. Récupérer les composants UI
        TextView tvType = findViewById(R.id.detailType);
        TextView tvDate = findViewById(R.id.detailDate);
        TextView tvDesc = findViewById(R.id.detailDesc);

        // 2. Activer la flèche de retour (déplacé ici)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Détail intervention");
        }

        // 3. Récupérer l'objet Intervention
        Intervention inter = (Intervention) getIntent().getSerializableExtra("intervention_selectionnee");

        if (inter != null) {
            tvType.setText(inter.getType());
            tvDesc.setText(inter.getDescription());

            if (inter.getDatePrevue() != null) {
                tvDate.setText(formatteDateEurope(inter.getDatePrevue()));
            } else {
                tvDate.setText("Non planifiée");
            }
        }
    }

    private String formatteDateEurope(String dateBrute) {
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            Date date = sdfSource.parse(dateBrute);
            return sdfDestination.format(date);
        } catch (ParseException e) {
            return dateBrute;
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