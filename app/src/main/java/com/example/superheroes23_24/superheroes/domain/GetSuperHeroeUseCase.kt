package com.example.superheroes23_24.superheroes.domain

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

class GetSuperHeroeUseCase(
    private val superHeroeRepository: SuperHeroeRepository,
    private val biographyRepository: BiographyRepository,
    private val powerStatsRepository: PowerStatsRepository,
    private val connectionsRepository: ConnectionsRepository

) {

    suspend operator fun invoke(id: Int): Either<ErrorApp, SuperHeroeDetail>{
        val superHeroe = superHeroeRepository.getSuperHeroeId(id)

        val detail = superHeroe.map {
            val biography = biographyRepository.getBiography(it.id).get()
            val stats = powerStatsRepository.getPowerStats(it.id).get()
            val connection = connectionsRepository.getConnections(it.id).get()

            SuperHeroeDetail(
                it.id,
                it.name,
                it.getImageLg(),
                it.image,
                biography.fullName,
                connection.affiliation,
                stats.intelligence,
                stats.speed,
                stats.combat
            )
        }
        return detail
    }

    data class SuperHeroeDetail(
        val id: Int,
        val name: String,
        val bannerImage: String,
        val images: List<String>,
        val fullName: String,
        val affiliation: String,
        val intelligence: Int,
        val speed: Int,
        val combat: Int
    )
}