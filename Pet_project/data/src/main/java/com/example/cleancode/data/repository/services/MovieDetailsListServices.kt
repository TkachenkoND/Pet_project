package com.example.cleancode.data.repository.services

import com.example.cleancode.domain.models.MovieDetailsList
import retrofit2.http.GET

interface MovieDetailsListServices {
    @GET("/3/movie/popular?api_key=f5cf8d703a88ed012d1f7e45101e828e&language=en-US&page=1")
    suspend fun getFilm(): MovieDetailsList
}