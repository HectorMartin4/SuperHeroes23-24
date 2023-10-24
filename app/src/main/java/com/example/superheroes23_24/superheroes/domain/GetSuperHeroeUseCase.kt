package com.example.superheroes23_24.superheroes.domain

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

class GetSuperHeroeUseCase(private val repository: SuperHeroeRepository) {

    operator fun invoke(): Either<ErrorApp, List<SuperHeroe>> = repository.get()
}