package com.example.monappliimmo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On appelle la configuration de la navigation
        setupNavigation();
    }

    private void setupNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Écran par défaut (Home)
        chargerFragment(new HomeFragment());

        // Gestion des clics
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_appartements) {
                return chargerFragment(new HomeFragment());
            } else if (id == R.id.nav_batiments) {
                // return chargerFragment(new BatimentFragment());
            }
            return false;
        });
    }

    private boolean chargerFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}