package com.example.superheroes23_24.superheroes.data.remote

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.superheroes.data.remote.api.ApiClient
import com.example.superheroes23_24.superheroes.data.remote.api.toDomain
import com.example.superheroes23_24.superheroes.domain.SuperHeroe

class SuperHeroeRemoteDataSource {

    private val apiClient = ApiClient()

    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>>{
        return apiClient.getSuperHeroes().map {
            it.subList(0, 20).map {
                it.toDomain()
            }
        }
    }

    suspend fun getSuperHeroeId(id: Int): Either<ErrorApp, SuperHeroe> = apiClient.getSuperHeroeId(id).map {
        it.toDomain()
    }


}