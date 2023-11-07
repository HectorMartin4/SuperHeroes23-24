package com.example.superheroes23_24.superheroes.data.remote.api

import com.google.gson.annotations.SerializedName

data class SuperHeroeApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images") val image: ImageApiModel
)

data class BiographyApiModel(
    @SerializedName("fullName") val fullName: String
)

data class WorkApiModel(
    @SerializedName("occupation") val occupation: String
)

data class ImageApiModel(
    @SerializedName("xs") val xs: String,
    @SerializedName("sm") val sm: String,
    @SerializedName("md") val md: String,
    @SerializedName("lg") val lg: String
)

data class PowerStatsApiModel(
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("speed") val speed: Int,
    @SerializedName("combat") val combat: Int
)

data class ConnectionsApiModel(
    @SerializedName("groupAffiliation") val affiliation: String
)