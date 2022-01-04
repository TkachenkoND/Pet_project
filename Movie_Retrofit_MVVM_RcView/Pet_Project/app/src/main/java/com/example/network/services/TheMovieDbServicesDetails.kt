package com.example.network.services

import com.example.network.data.TheMovieDbFilmActorList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface TheMovieDbServicesDetails {
    @GET("/3/movie/{movie_id}/credits?api_key=f5cf8d703a88ed012d1f7e45101e828e&language=en-US")
    fun getActor(@Path("movie_id") movieId: Int): Call<TheMovieDbFilmActorList>
}