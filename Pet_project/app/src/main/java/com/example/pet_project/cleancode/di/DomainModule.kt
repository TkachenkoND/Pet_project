package com.example.pet_project.cleancode.di

import com.example.cleancode.domain.usecase.LoadDetailsMoviesByIdUseCase
import com.example.cleancode.domain.usecase.LoadMoviesListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<LoadDetailsMoviesByIdUseCase> { LoadDetailsMoviesByIdUseCase(get()) }
    factory<LoadMoviesListUseCase> { LoadMoviesListUseCase(get()) }
}