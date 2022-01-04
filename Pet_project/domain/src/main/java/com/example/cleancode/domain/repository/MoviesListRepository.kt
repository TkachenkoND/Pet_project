package com.example.cleancode.domain.repository

import com.example.cleancode.domain.models.MovieDetailsList

interface MoviesListRepository {
    suspend fun getMoviesList(): MovieDetailsList
}