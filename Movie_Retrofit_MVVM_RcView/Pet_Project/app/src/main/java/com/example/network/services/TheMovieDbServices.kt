package com.example.network.services

import com.example.network.data.TheMovieDbFilmResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbServices {
    @GET("/3/movie/popular?api_key=f5cf8d703a88ed012d1f7e45101e828e&language=en-US&page=")
    fun getFilm(@Query("page") page: Int): Call<TheMovieDbFilmResponse>
}