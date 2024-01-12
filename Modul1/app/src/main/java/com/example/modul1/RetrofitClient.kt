package com.example.modul1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response
import retrofit2.http.GET

object RetrofitClient {
    private const val BASE_URL = "https://swapi.dev/api/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface SwapiService {
    @GET("people/1/") // Example endpoint, replace with your desired endpoint
    suspend fun getPerson(): Response<StarWarsCharacter>
}