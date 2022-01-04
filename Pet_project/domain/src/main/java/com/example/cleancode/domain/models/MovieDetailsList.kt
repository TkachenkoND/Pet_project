package com.example.cleancode.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsList(
    @Json(name = "results")
    var filmDetails: List<MovieDetails>,
)
