package com.example.monappliimmo

import java.io.Serializable

class Contrat : Serializable {
    var id: Long = 0
    var montantBrut: Double = 0.0
    var montantCharge: Double = 0.0
    var dateDebut: String? = null
    var dateFin: String? = null
    var locataire: Locataire? = null
}