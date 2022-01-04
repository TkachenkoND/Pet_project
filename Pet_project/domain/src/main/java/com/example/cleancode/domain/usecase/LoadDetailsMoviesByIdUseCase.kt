package com.example.cleancode.domain.usecase

import com.example.cleancode.domain.repository.DetailsMoviesListRepository

class LoadDetailsMoviesByIdUseCase(private val detailsMoviesListRepository: DetailsMoviesListRepository) {

    suspend fun loadDetailsMoviesByIdUseCase(id: Int) = detailsMoviesListRepository.getMovieDetails(id)
}