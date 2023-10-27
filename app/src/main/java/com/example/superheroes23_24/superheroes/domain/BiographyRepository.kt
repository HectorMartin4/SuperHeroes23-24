package com.example.superheroes23_24.superheroes.domain

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

interface BiographyRepository {

    suspend fun getBiography(id: Int): Either<ErrorApp, Biography>
}