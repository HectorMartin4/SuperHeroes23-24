package com.example.superheroes23_24.superheroes.domain

import android.media.Image
import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp

class GetSuperHeroeFeedUseCase(
    private val superHeroeRepository: SuperHeroeRepository,
    private val biographyRepository: BiographyRepository,
    private val workRepository: WorkRepository
) {

    suspend operator fun invoke(): Either<ErrorApp, List<SuperHeroeFeed>> {
        val superHeroes = superHeroeRepository.getSuperHeroes()


        val feed = superHeroes.map {
            it.map {superHeroe ->
                val work = workRepository.getWork(superHeroe.id).get()
                val biography = biographyRepository.getBiography(superHeroe.id).get()

                SuperHeroeFeed(
                    superHeroe.id,
                    superHeroe.name,
                    superHeroe.getImageLg(),
                    biography.fullName,
                    work.occupation
                )
            }
        }
        return feed
    }

    data class SuperHeroeFeed(
        val id: Int,
        val name: String,
        val image: String,
        val fullName: String,
        val occupation: String
    )
}