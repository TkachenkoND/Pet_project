package com.example.cleancode.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetails(
    @Json(name = "id")
    val filmId: String,
    @Json(name = "poster_path")
    val filmImage: String?,
    @Json(name = "original_title")
    val filmName: String,
    @Json(name = "release_date")
    val movieReleaseDate: String,
    val overview: String,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "vote_average")
    val rating: String,
)
