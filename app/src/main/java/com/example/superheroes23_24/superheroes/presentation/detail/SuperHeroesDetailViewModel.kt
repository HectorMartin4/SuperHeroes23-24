package com.example.superheroes23_24.superheroes.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroes23_24.app.errors.ErrorApp
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeUseCase
import com.example.superheroes23_24.superheroes.presentation.SuperHeroesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroesDetailViewModel(private val getSuperHeroeUseCase: GetSuperHeroeUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData<SuperHeroeDetailUiState>()
    val uiStateDetail: LiveData<SuperHeroeDetailUiState> = _uiState

    private fun responseError(errorApp: ErrorApp){
        _uiState.postValue(SuperHeroeDetailUiState(errorApp = errorApp))
    }

    fun loadDetail(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val detail = getSuperHeroeUseCase.invoke(id)
            detail.fold(
                { responseError(it)},
                {
                    _uiState.postValue(
                        SuperHeroeDetailUiState(
                            superHeroes = detail.get()
                        )
                    )
                }
            )
        }
    }


    data class SuperHeroeDetailUiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val superHeroes: GetSuperHeroeUseCase.SuperHeroeDetail? = null
    )

}