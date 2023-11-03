package com.example.superheroes23_24.superheroes.data.remote

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.superheroes.data.remote.api.ApiClient
import com.example.superheroes23_24.superheroes.data.remote.api.toDomain
import com.example.superheroes23_24.superheroes.domain.Connections
import com.example.superheroes23_24.superheroes.domain.ConnectionsRepository

class ConnectionsRemoteDataSource : ConnectionsRepository {

    private val apiClient = ApiClient()

    override suspend fun getConnections(id: Int): Either<ErrorApp, Connections> =
        apiClient.getConnections(id).map { it.toDomain() }
}