package com.example.pet_project.cleancode.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleancode.domain.models.ActorDetailsList
import com.example.cleancode.domain.usecase.LoadDetailsMoviesByIdUseCase
import kotlinx.coroutines.launch

class MovieDetailsActivityViewModel(private val loadDetailsMoviesByIdUseCase :LoadDetailsMoviesByIdUseCase) :
    ViewModel() {

    private val _moviesDetail = MutableLiveData<ActorDetailsList>()
    val moviesDetail: LiveData<ActorDetailsList> = _moviesDetail

    fun loadMovieDetail(id: Int){
        viewModelScope.launch {
            try {
                _moviesDetail.value = loadDetailsMoviesByIdUseCase.loadDetailsMoviesByIdUseCase(id)
            } catch (e: Exception) {
                print(e)
            }
        }
    }

}