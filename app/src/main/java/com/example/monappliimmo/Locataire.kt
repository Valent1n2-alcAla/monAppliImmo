package com.example.monappliimmo

import java.io.Serializable

class Locataire : Serializable {
    var id: Long = 0
    var nom: String? = null
    var prenom: String? = null
    var mail: String? = null
    var telephone: String? = null
    var dateNaissance: String? = null
}