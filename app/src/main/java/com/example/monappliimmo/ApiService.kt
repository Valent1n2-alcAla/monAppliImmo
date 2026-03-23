package com.example.monappliimmo

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/appartements")
    fun getAppartements(): Call<List<Appartement>>

    @GET("api/batiments")
    fun getBatiments(): Call<List<Batiment>>

    @GET("api/interventions")
    fun getInterventions(): Call<List<Intervention>>
}