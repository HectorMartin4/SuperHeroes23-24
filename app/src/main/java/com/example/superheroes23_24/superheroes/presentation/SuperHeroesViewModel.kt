package com.example.superheroes23_24.superheroes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroes23_24.app.errors.Either
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.app.errors.left
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroesViewModel(
    private val getSuperHeroeFeedUseCase: GetSuperHeroeFeedUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<SuperHeroeUiState>()
    val uiState: LiveData<SuperHeroeUiState> = _uiState

    fun loadSuperHeroes(){
        viewModelScope.launch(Dispatchers.IO) {
            val feed = getSuperHeroeFeedUseCase.invoke()
            feed.fold(
                { responseError(it)},
                {
                    _uiState.postValue(
                        SuperHeroeUiState(
                            superHeroes = feed.get()
                        )
                    )
                }
            )
        }
    }

    private fun responseError(errorApp: ErrorApp): Either<ErrorApp, GetSuperHeroeFeedUseCase.SuperHeroeFeed> = errorApp.left()

    data class SuperHeroeUiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val superHeroes: List<GetSuperHeroeFeedUseCase.SuperHeroeFeed> = emptyList()
    )
}