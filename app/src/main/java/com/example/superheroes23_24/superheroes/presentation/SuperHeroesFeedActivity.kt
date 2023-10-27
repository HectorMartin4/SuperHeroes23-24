package com.example.superheroes23_24.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes23_24.R
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.app.extensions.GsonJSerializer
import com.example.superheroes23_24.app.extensions.hide
import com.example.superheroes23_24.app.extensions.visible
import com.example.superheroes23_24.databinding.ActivitySuperHeroesFeedBinding
import com.example.superheroes23_24.superheroes.data.SuperHeroeDataRepository
import com.example.superheroes23_24.superheroes.data.local.XmlLocalDataSource
import com.example.superheroes23_24.superheroes.data.remote.BiographyRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.SuperHeroeRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.WorkRemoteDataSource
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase
import com.example.superheroes23_24.superheroes.presentation.adapter.SuperHeroesAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton

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

    private val skeleton: Skeleton by lazy {
        binding.listSuperHeroeFeed.applySkeleton(R.layout.view_item_super_heroes_feed, 8)
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
            if(it.isLoading){
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()

                if(it.errorApp != null) {
                    showError(it.errorApp)
                } else {
                    superHeroesAdapter.submitList(it.superHeroes)
                }
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun showError (error : ErrorApp){
        binding.listSuperHeroeFeed.hide()
        binding.viewError.layoutError.visible()

        when(error) {
            ErrorApp.UnknowError -> binding.viewError.labelMesaggeError.text =
                getString(R.string.label_unknow_error)
        }
    }
}