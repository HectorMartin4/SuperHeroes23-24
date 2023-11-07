package com.example.superheroes23_24.superheroes.data.remote

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.superheroes.data.remote.api.ApiClient
import com.example.superheroes23_24.superheroes.data.remote.api.toDomain
import com.example.superheroes23_24.superheroes.domain.PowerStats
import com.example.superheroes23_24.superheroes.domain.PowerStatsRepository

class PowerStatsRemoteDataSource : PowerStatsRepository {

    private val apiClient = ApiClient()

    override suspend fun getPowerStats(id: Int): Either<ErrorApp, PowerStats> =
        apiClient.getPowerStats(id).map { it.toDomain() }
}