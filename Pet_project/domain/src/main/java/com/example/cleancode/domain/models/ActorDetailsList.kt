package com.example.cleancode.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActorDetailsList(
    @Json(name = "cast")
    val cast: List<ActorDetails>?,
)
