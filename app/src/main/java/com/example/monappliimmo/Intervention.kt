package com.example.monappliimmo

import java.io.Serializable

class Intervention : Serializable {
    // Getters
    val id: Long? = null

    // Setters (Optionnels pour Retrofit mais conseillés)
    @JvmField
    var type: String? = null
    @JvmField
    var description: String? = null
    @JvmField
    var datePrevue: String? = null

    // Ici, on déclare l'objet Appartement car ton JSON contient un bloc "appartement": {...}
    var appartement: Appartement? = null
}