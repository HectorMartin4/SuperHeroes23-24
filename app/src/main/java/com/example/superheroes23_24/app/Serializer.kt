package com.example.superheroes23_24.app

import com.google.gson.Gson

interface Serializer {
    fun <T> fromJson(json: String, classObj: Class<T>): T
    fun <T> toJson(obj: T, classObj: Class<T>): String
}

class GsonJSerializer : Serializer {

    private val gson = Gson()

    override fun <T> fromJson(json: String, classObj: Class<T>): T {
        return gson.fromJson(json, classObj)
    }

    override fun <T> toJson(obj: T, classObj: Class<T>): String {
        return gson.toJson(obj, classObj)
    }
}