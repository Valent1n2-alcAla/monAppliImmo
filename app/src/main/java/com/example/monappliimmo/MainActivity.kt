package com.example.monappliimmo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Écran par défaut
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