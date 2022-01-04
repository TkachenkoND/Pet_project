package com.example.pet_project.cleancode.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleancode.domain.models.MovieDetailsList
import com.example.cleancode.domain.usecase.LoadMoviesListUseCase
import kotlinx.coroutines.launch

class MovieListActivityViewModel(private val loadMoviesListUseCase: LoadMoviesListUseCase) :
    ViewModel() {

    private val _moviesList = MutableLiveData<MovieDetailsList>()
    val moviesListLiveData: LiveData<MovieDetailsList> = _moviesList

    fun loadMovieList() {
        viewModelScope.launch {
            try {
                _moviesList.value = loadMoviesListUseCase.loadMoviesList()
            } catch (e: Exception) {
                print(e)
            }
        }
    }

}