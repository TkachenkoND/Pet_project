package com.example.network.data

import com.squareup.moshi.Json

data class TheMoviesDBActor(
    @Json(name = "profile_path")
    val actorImage: String?,
    @Json(name = "name")
    val actorName: String?,
)
