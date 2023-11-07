package com.example.superheroes23_24.superheroes.data.remote

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.superheroes.data.remote.api.ApiClient
import com.example.superheroes23_24.superheroes.data.remote.api.BiographyApiModel
import com.example.superheroes23_24.superheroes.data.remote.api.toDomain
import com.example.superheroes23_24.superheroes.domain.Biography
import com.example.superheroes23_24.superheroes.domain.BiographyRepository
import com.example.superheroes23_24.superheroes.domain.SuperHeroe

class BiographyRemoteDataSource : BiographyRepository{

    private val apiClient = ApiClient()

    override suspend fun getBiography(id: Int): Either<ErrorApp, Biography> = apiClient.getBiography(id).map {
        it.toDomain()
    }
}