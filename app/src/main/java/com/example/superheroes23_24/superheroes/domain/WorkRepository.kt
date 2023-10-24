package com.example.superheroes23_24.superheroes.domain

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

interface WorkRepository {

    fun getWork(): Either<ErrorApp, Work>
}