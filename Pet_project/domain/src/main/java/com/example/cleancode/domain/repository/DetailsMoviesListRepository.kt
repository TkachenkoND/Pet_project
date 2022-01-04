package com.example.cleancode.domain.repository

import com.example.cleancode.domain.models.ActorDetailsList

interface DetailsMoviesListRepository {
    suspend fun getMovieDetails(id: Int): ActorDetailsList
}