package com.example.superheroes23_24.superheroes.data.local

import android.content.Context
import com.example.superheroes23_24.app.extensions.Serializer
import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.app.errors.left
import com.example.superheroes23_24.app.errors.right
import com.example.superheroes23_24.superheroes.domain.SuperHeroe

class XmlLocalDataSource(private val context: Context, private val serializer: Serializer) {

    private val sharedPref = context.getSharedPreferences("SuperHeroes", Context.MODE_PRIVATE)

    fun saveSuperHeroes(superHeroes: List<SuperHeroe>): Either<ErrorApp, Boolean> {
        return try {
            superHeroes.map { superHeroe ->
                sharedPref.edit().apply{
                    putString(superHeroe.id.toString(),
                        serializer.toJson(superHeroe, SuperHeroe::class.java)
                    )
                }.apply()
            }
            true.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowError.left()
        }
    }

    fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroe>> {
        return try {
            sharedPref.all.map {
                serializer
                    .fromJson(it.value as String, SuperHeroe::class.java)

            }.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowError.left()
        }
    }


    fun getSuperHeroeId(id: Int): Either<ErrorApp, SuperHeroe> {
        return try {
            serializer
                .fromJson(sharedPref.getString(id.toString(), null)!!, SuperHeroe::class.java)
                .right()
        } catch (ex: Exception) {
            ErrorApp.UnknowError.left()
        }
    }


}