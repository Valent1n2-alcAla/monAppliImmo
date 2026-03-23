package com.example.monappliimmo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/appartements")
    Call<List<Appartement>> getAppartements();

    @GET("api/batiments")
    Call<List<Batiment>> getBatiments();
}