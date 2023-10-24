package com.example.superheroes23_24.superheroes.domain

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

interface SuperHeroeRepository {

    fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>>

    fun getSuperHeroeId(): Either<ErrorApp, SuperHeroe>

}