package com.example.superheroes23_24.superheroes.data

import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.superheroes.data.local.XmlLocalDataSource
import com.example.superheroes23_24.superheroes.data.remote.SuperHeroeRemoteDataSource
import com.example.superheroes23_24.superheroes.domain.SuperHeroe
import com.example.superheroes23_24.superheroes.domain.SuperHeroeRepository

class SuperHeroeDataRepository(
    private val localSource: XmlLocalDataSource,
    private val remoteSource: SuperHeroeRemoteDataSource
) : SuperHeroeRepository {

    override suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>> {
        var localSuperHeroes = localSource.getSuperHeroes()

        return if(localSuperHeroes.isLeft() || localSuperHeroes.get().isEmpty()  ) {
            localSuperHeroes = remoteSource.getSuperHeroes()

            remoteSource.getSuperHeroes().map {
                localSource.saveSuperHeroes(it)
            }
            localSuperHeroes

        } else {
            localSuperHeroes
        }
    }

    override suspend fun getSuperHeroeId(id: Int): Either<ErrorApp, SuperHeroe> {
        var superHeroe = localSource.getSuperHeroeId(id)

        return if (superHeroe.isLeft()) {
            superHeroe = remoteSource.getSuperHeroeId(id)

            superHeroe

        } else {
            superHeroe
        }
    }
}