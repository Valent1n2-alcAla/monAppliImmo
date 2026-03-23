package com.example.monappliimmo

import java.io.Serializable

class Loyer : Serializable {
    var id: Long = 0
    var montantPaye: Double = 0.0
    var datePaiement: String? = null
    var contrat: Contrat? = null
}