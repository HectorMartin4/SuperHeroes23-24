package com.example.superheroes23_24

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.superheroes23_24.app.GsonJSerializer
import com.example.superheroes23_24.superheroes.data.SuperHeroeDataRepository
import com.example.superheroes23_24.superheroes.data.local.XmlLocalDataSource
import com.example.superheroes23_24.superheroes.data.remote.RemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.api.ApiClient
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSuperHeroes()
    }


    fun getSuperHeroes() {

        CoroutineScope(Dispatchers.IO).launch {
            val superHeroe = SuperHeroeDataRepository(XmlLocalDataSource(this@MainActivity, GsonJSerializer()), RemoteDataSource())

            superHeroe.getSuperHeroes()

        }
    }
}