package com.example.superheroes23_24.superheroes.data.remote

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.superheroes.data.remote.api.ApiClient
import com.example.superheroes23_24.superheroes.data.remote.api.toDomain
import com.example.superheroes23_24.superheroes.domain.Work
import com.example.superheroes23_24.superheroes.domain.WorkRepository

class WorkRemoteDataSource : WorkRepository {

    private val apiClient = ApiClient()

    override suspend fun getWork(id: Int): Either<ErrorApp, Work> = apiClient.getWork(id).map {
        it.toDomain()
    }
}