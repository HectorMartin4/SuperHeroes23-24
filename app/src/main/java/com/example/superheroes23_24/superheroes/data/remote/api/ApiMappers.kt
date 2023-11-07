package com.example.superheroes23_24.superheroes.data.remote.api

import com.example.superheroes23_24.superheroes.domain.Biography
import com.example.superheroes23_24.superheroes.domain.Connections
import com.example.superheroes23_24.superheroes.domain.PowerStats
import com.example.superheroes23_24.superheroes.domain.SuperHeroe
import com.example.superheroes23_24.superheroes.domain.Work

fun SuperHeroeApiModel.toDomain(): SuperHeroe {
    return SuperHeroe(
        this.id,
        this.name,
        listOf(this.image.xs, this.image.sm, this.image.md, this.image.lg)
    )
}

fun BiographyApiModel.toDomain(): Biography {
    return Biography(
        this.fullName
    )
}

fun WorkApiModel.toDomain(): Work {
    return Work(
        this.occupation
    )
}

fun PowerStatsApiModel.toDomain(): PowerStats {
    return PowerStats(
        this.intelligence,
        this.speed,
        this.combat
    )
}

fun ConnectionsApiModel.toDomain(): Connections {
    return Connections(
        this.affiliation
    )
}