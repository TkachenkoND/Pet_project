package com.example.cleancode.data.repository.services

import com.example.cleancode.domain.models.ActorDetailsList
import retrofit2.http.GET
import retrofit2.http.Path

interface ActorDetailsListServices {
    @GET("/3/movie/{movie_id}/credits?api_key=f5cf8d703a88ed012d1f7e45101e828e&language=en-US")
    suspend fun getActor(@Path("movie_id") movieId: Int): ActorDetailsList
}