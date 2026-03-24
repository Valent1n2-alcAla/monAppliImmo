package com.example.monappliimmo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Écran par défaut (Liste des appartements)
        chargerFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {

                R.id.nav_appartements -> {
                    chargerFragment(HomeFragment())
                    true
                }

                R.id.nav_batiments -> {
                    chargerFragment(BatimentFragment())
                    true
                }

                R.id.nav_interventions -> {
                    chargerFragment(InterventionFragment())
                    true
                }

                // Clic sur l'onglet "Compte" pour ouvrir ton superbe écran de login
                R.id.nav_compte -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    // On retourne false pour ne pas changer d'onglet visuellement
                    // car on change carrément de page (Activity)
                    false
                }

                else -> false
            }
        }
    }

    private fun chargerFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}