package com.example.superheroes23_24.superheroes.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes23_24.app.extensions.GsonJSerializer
import com.example.superheroes23_24.app.extensions.setUrl
import com.example.superheroes23_24.databinding.ActivitySuperHeroesDetailBinding
import com.example.superheroes23_24.superheroes.data.SuperHeroeDataRepository
import com.example.superheroes23_24.superheroes.data.local.XmlLocalDataSource
import com.example.superheroes23_24.superheroes.data.remote.BiographyRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.ConnectionsRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.PowerStatsRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.SuperHeroeRemoteDataSource
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeUseCase
import com.example.superheroes23_24.superheroes.presentation.adapter.SuperHeroesDetailAdapter

class SuperHeroesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroesDetailBinding
    private val superHeroesDetailAdapter = SuperHeroesDetailAdapter()

    val viewModel: SuperHeroesDetailViewModel by lazy {
        SuperHeroesDetailViewModel(
            GetSuperHeroeUseCase(
                SuperHeroeDataRepository(
                    XmlLocalDataSource(this, GsonJSerializer()),
                    SuperHeroeRemoteDataSource()
                ),
                BiographyRemoteDataSource(),
                PowerStatsRemoteDataSource(),
                ConnectionsRemoteDataSource(),
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.extras!!.getInt("id")
        setupObservers()
        setupView()
        viewModel.loadDetail(id)
    }

    private fun setupView(){
        binding.apply {
            listImagesDetail.layoutManager = LinearLayoutManager(
                this@SuperHeroesDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            listImagesDetail.adapter = superHeroesDetailAdapter
        }
    }

    private fun setupObservers(){
        val observer = Observer<SuperHeroesDetailViewModel.SuperHeroeDetailUiState>{
            val detail = it.superHeroes!!
            bindSuperHeroeDetail(detail)
        }
        viewModel.uiStateDetail.observe(this, observer)
    }

    private fun bindSuperHeroeDetail(bind: GetSuperHeroeUseCase.SuperHeroeDetail){
        binding.apply {
            title.text = bind.name
            imageDetail.setUrl(bind.bannerImage)
            fullName.text = bind.fullName
            description.text = bind.affiliation
            intelligence.text = bind.intelligence.toString()
            speed.text = bind.speed.toString()
            combat.text = bind.combat.toString()
            superHeroesDetailAdapter.loadItems(bind.images)
        }
    }
}