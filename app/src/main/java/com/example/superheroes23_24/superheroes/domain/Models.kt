package com.example.superheroes23_24.superheroes.domain

data class SuperHeroe(
    val id: Int,
    val name: String,
    val image: List<String>
){
    fun getImageXs() : String = image[0]
    fun getImageSm() : String = image[1]
    fun getImageMd() : String = image[2]
    fun getImageLg() : String = image[3]
}

data class Biography(
    val fullName: String
)

data class Work(
    val occupation: String
)