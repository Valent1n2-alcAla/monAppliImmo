package com.example.monappliimmo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Cette méthode permet d'appeler .getApi() après l'instance
    fun getApi(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "http://10.0.2.2:9005/"

        @Volatile
        private var fieldInstance: RetrofitClient? = null

        // Cette méthode CORRIGE l'erreur 'Unresolved reference getInstance'
        @JvmStatic
        fun getInstance(): RetrofitClient {
            return fieldInstance ?: synchronized(this) {
                fieldInstance ?: RetrofitClient().also { fieldInstance = it }
            }
        }
    }
}