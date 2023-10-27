package com.example.superheroes23_24.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes23_24.app.extensions.GsonJSerializer
import com.example.superheroes23_24.databinding.ActivitySuperHeroesFeedBinding
import com.example.superheroes23_24.superheroes.data.SuperHeroeDataRepository
import com.example.superheroes23_24.superheroes.data.local.XmlLocalDataSource
import com.example.superheroes23_24.superheroes.data.remote.BiographyRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.SuperHeroeRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.WorkRemoteDataSource
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase
import com.example.superheroes23_24.superheroes.presentation.adapter.SuperHeroesAdapter

class SuperHeroesFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroesFeedBinding
    private val superHeroesAdapter = SuperHeroesAdapter()

    val viewModel: SuperHeroesViewModel by lazy {
        SuperHeroesViewModel(
            GetSuperHeroeFeedUseCase(
                SuperHeroeDataRepository(
                    XmlLocalDataSource(
                        this,
                        GsonJSerializer()
                    ), SuperHeroeRemoteDataSource()
                ),
                BiographyRemoteDataSource(),
                WorkRemoteDataSource()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroesFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupView()
        viewModel.loadSuperHeroes()
    }

    private fun setupView(){
        binding.apply {
            listSuperHeroeFeed.layoutManager = LinearLayoutManager(
                this@SuperHeroesFeedActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            listSuperHeroeFeed.adapter = superHeroesAdapter
        }
    }

    private fun setupObservers() {
        val observer = Observer<SuperHeroesViewModel.SuperHeroeUiState> {
            superHeroesAdapter.setDataList(it.superHeroes)
        }
        viewModel.uiState.observe(this, observer)
    }
}