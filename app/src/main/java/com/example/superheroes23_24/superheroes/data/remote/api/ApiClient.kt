package com.example.superheroes23_24.superheroes.data.remote.api

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.app.errors.left
import com.example.superheroes23_24.app.errors.right
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val urlEndPoint = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/"
    private var apiEndPoint: ApiServices

    init {
        apiEndPoint = buildApiEndPoint()
    }

    private fun buildClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buildApiEndPoint(): ApiServices {
        return buildClient().create(ApiServices::class.java)

    }

    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroeApiModel>> {
        val response = apiEndPoint.getSuperHeroes()

        return if (response.isSuccessful){
            response.body()!!.right()
        } else {
            ErrorApp.UnknowError.left()
        }
    }

    suspend fun getSuperHeroeId(id: Int): Either<ErrorApp, SuperHeroeApiModel> {
        val response = apiEndPoint.getSuperHeroeId(id)

        return if (response.isSuccessful){
            response.body()!!.right()
        } else {
            ErrorApp.UnknowError.left()
        }
    }

    suspend fun getBiography(id: Int): Either<ErrorApp, BiographyApiModel> {
        val response = apiEndPoint.getBiography(id)

        return if (response.isSuccessful){
            response.body()!!.right()
        } else {
            ErrorApp.UnknowError.left()
        }
    }

    suspend fun getWork(id: Int): Either<ErrorApp, WorkApiModel> {
        val response = apiEndPoint.getWork(id)

        return if (response.isSuccessful){
            response.body()!!.right()
        } else {
            ErrorApp.UnknowError.left()
        }
    }

    suspend fun getPowerStats(id: Int): Either<ErrorApp, PowerStatsApiModel> {
        val response = apiEndPoint.getPowerStats(id)

        return if (response.isSuccessful){
            response.body()!!.right()
        } else {
            ErrorApp.UnknowError.left()
        }
    }

    suspend fun getConnections(id: Int): Either<ErrorApp, ConnectionsApiModel> {
        val response = apiEndPoint.getConnections(id)

        return if (response.isSuccessful){
            response.body()!!.right()
        } else {
            ErrorApp.UnknowError.left()
        }
    }
}