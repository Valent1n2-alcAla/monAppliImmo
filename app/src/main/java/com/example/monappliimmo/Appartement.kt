package com.example.monappliimmo

import java.io.Serializable

class Appartement : Serializable {
    @JvmField
    var id: Long = 0
    @JvmField
    var numero: Int = 0
    @JvmField
    var description: String? = null
    @JvmField
    var surface: Double = 0.0
    @JvmField
    var nombrePieces: Int = 0
    @JvmField
    var batiment: Batiment? = null
    @JvmField
    var contrats: MutableList<Contrat?>? = null

    var interventions: MutableList<Intervention?>? = null
}