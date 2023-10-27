package com.example.superheroes23_24.superheroes.domain

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

interface SuperHeroeRepository {

    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>>

    suspend fun getSuperHeroeId(id: Int): Either<ErrorApp, SuperHeroe>

}