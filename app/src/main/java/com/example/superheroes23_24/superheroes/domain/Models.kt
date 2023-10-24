package com.example.superheroes23_24.superheroes.domain

data class SuperHeroe(
    val id: Int,
    val name: String,
    val image: Image,
    val biography: Biography,
    val work: Work
)

data class Biography(
    val fullName: String
)

data class Work(
    val occupation: String
)

data class Image(
    val size: String
)