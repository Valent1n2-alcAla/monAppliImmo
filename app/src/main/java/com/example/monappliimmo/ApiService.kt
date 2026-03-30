package com.example.monappliimmo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/appartements")
    fun getAppartements(): Call<List<Appartement>>

    @GET("api/batiments")
    fun getBatiments(): Call<List<Batiment>>

    @GET("api/interventions")
    fun getInterventions(): Call<List<Intervention>>

    @GET("api/loyers/appartement/{id}")
    suspend fun getLoyersByAppartement(@Path("id") id: Long): List<Loyer>

    @GET("api/loyers/locataire/{id}")
    suspend fun getLoyersByLocataire(@Path("id") id: Long): List<Loyer>
}