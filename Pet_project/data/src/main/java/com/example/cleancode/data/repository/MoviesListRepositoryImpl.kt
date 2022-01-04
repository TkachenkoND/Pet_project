package com.example.cleancode.data.repository

import com.example.cleancode.data.repository.services.MovieDetailsListServices
import com.example.cleancode.domain.repository.MoviesListRepository

class MoviesListRepositoryImpl(private val movieDetailsListServices: MovieDetailsListServices):
    MoviesListRepository {
    override suspend fun getMoviesList() = movieDetailsListServices.getFilm()

}