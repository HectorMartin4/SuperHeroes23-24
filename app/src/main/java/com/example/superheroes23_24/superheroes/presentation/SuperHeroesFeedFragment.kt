package com.example.superheroes23_24.superheroes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes23_24.MainActivity
import com.example.superheroes23_24.R
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.app.extensions.GsonJSerializer
import com.example.superheroes23_24.app.extensions.hide
import com.example.superheroes23_24.app.extensions.visible
import com.example.superheroes23_24.databinding.FragmentSuperHeroesFeedBinding
import com.example.superheroes23_24.superheroes.data.SuperHeroeDataRepository
import com.example.superheroes23_24.superheroes.data.local.XmlLocalDataSource
import com.example.superheroes23_24.superheroes.data.remote.BiographyRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.SuperHeroeRemoteDataSource
import com.example.superheroes23_24.superheroes.data.remote.WorkRemoteDataSource
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase
import com.example.superheroes23_24.superheroes.presentation.adapter.SuperHeroesAdapter
import com.example.superheroes23_24.superheroes.presentation.detail.SuperHeroesDetailFragment
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton

class SuperHeroesFeedFragment : Fragment() {

    private var _binding: FragmentSuperHeroesFeedBinding? = null
    private val binding get() = _binding!!
    private val superHeroesAdapter = SuperHeroesAdapter()

    val viewModel: SuperHeroesViewModel by lazy {
        SuperHeroesViewModel(
            GetSuperHeroeFeedUseCase(
                SuperHeroeDataRepository(
                    XmlLocalDataSource(
                        requireContext(),
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperHeroesFeedBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadSuperHeroes()
    }

    private fun setupView(){
        binding.apply {
            listSuperHeroeFeed.layoutManager = LinearLayoutManager(
                requireContext(),
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
                    superHeroesAdapter.setOnClickDetail {
                        navigateToDetail(it)
                    }
                }
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    fun navigateToDetail(id: Int){
        val result = id
        (activity as MainActivity).changeFragment(SuperHeroesDetailFragment.newInstance())
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
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