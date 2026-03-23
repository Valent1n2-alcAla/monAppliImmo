package com.example.monappliimmo

import java.io.Serializable

class Batiment : Serializable {
    var id: Long = 0
    var adresse: String? = null
    var ville: String? = null
    var appartements: List<Appartement>? = null
}