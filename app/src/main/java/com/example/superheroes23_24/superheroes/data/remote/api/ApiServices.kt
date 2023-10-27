package com.example.superheroes23_24.superheroes.data.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("all.json")
    suspend fun getSuperHeroes(): Response<List<SuperHeroeApiModel>>

    @GET("id/{id}.json")
    suspend fun getSuperHeroeId(@Path("id") id: Int): Response<SuperHeroeApiModel>

    @GET("biography/{id}.json")
    suspend fun getBiography(@Path("id") id: Int): Response<BiographyApiModel>

    @GET("work/{id}.json")
    suspend fun getWork(@Path("id") id: Int): Response<WorkApiModel>

}