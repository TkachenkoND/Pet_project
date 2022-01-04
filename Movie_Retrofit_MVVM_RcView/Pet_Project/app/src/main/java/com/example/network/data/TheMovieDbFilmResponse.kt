package com.example.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TheMovieDbFilmResponse(
    @Json(name = "results")
    val filmDetails: List<TheMovieDbDetails>,
)
