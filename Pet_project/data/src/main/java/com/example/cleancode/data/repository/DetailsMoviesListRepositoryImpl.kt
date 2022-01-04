package com.example.cleancode.data.repository

import com.example.cleancode.data.repository.services.ActorDetailsListServices
import com.example.cleancode.domain.repository.DetailsMoviesListRepository

class DetailsMoviesListRepositoryImpl(private val actorDetailsListServicesApi: ActorDetailsListServices) :
    DetailsMoviesListRepository {

    override suspend fun getMovieDetails(id: Int) = actorDetailsListServicesApi.getActor(id)

}