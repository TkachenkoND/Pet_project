package com.example.cleancode.domain.usecase

import com.example.cleancode.domain.repository.MoviesListRepository

class LoadMoviesListUseCase(private val moviesListRepository: MoviesListRepository) {

    suspend fun loadMoviesList() = moviesListRepository.getMoviesList()
}