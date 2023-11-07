package com.example.superheroes23_24.superheroes.domain

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

interface PowerStatsRepository {

    suspend fun getPowerStats(id: Int): Either<ErrorApp, PowerStats>
}