package com.example.superheroes23_24.superheroes.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes23_24.app.extensions.GsonJSerializer
import com.example.superheroes23_24.app.extensions.setUrl
import com.example.superheroes23_24.databinding.FragmentSuperHeroesDetailBinding
import com.example.superheroes23_24.superheroes.data.SuperHeroeDataRepository
import com.example.superheroes23_24.superheroes.data.local.XmlLocalDataSource
import com.example.superheroes23_24.superheroes.data.remote.BiographyRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.ConnectionsRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.PowerStatsRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.SuperHeroeRemoteDataSource
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeUseCase
import com.example.superheroes23_24.superheroes.presentation.adapter.SuperHeroesDetailAdapter

class SuperHeroesDetailFragment : Fragment() {


    private var _binding: FragmentSuperHeroesDetailBinding? = null
    private val binding get() = _binding!!
    private val superHeroesDetailAdapter = SuperHeroesDetailAdapter()

    val viewModel: SuperHeroesDetailViewModel by lazy {
        SuperHeroesDetailViewModel(
            GetSuperHeroeUseCase(
                SuperHeroeDataRepository(
                    XmlLocalDataSource(requireContext(), GsonJSerializer()),
                    SuperHeroeRemoteDataSource()
                ),
                BiographyRemoteDataSource(),
                PowerStatsRemoteDataSource(),
                ConnectionsRemoteDataSource(),
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroesDetailBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            val result = bundle.getInt("bundleKey")
            viewModel.loadDetail(result)
        }
    }


    private fun setupView(){
        binding.apply {
            listImagesDetail.layoutManager = LinearLayoutManager(
                requireContext(),
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
        viewModel.uiStateDetail.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    companion object {
        fun newInstance() = SuperHeroesDetailFragment()
    }
}