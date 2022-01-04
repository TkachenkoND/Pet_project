package com.example.cleancode.domain.models

import com.squareup.moshi.Json

data class ActorDetails(
    @Json(name = "profile_path")
    val actorImage: String?,
    @Json(name = "name")
    val actorName: String?,
)
