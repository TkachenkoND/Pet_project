package com.example.pet_project.cleancode.di

import com.example.pet_project.cleancode.presentation.view.activity.MovieDetailsActivity
import com.example.pet_project.cleancode.presentation.view.activity.MoviesListActivity
import com.example.pet_project.cleancode.presentation.viewmodel.MovieDetailsActivityViewModel
import com.example.pet_project.cleancode.presentation.viewmodel.MovieListActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MovieListActivityViewModel>() { MovieListActivityViewModel(get()) }
    viewModel<MovieDetailsActivityViewModel>() { MovieDetailsActivityViewModel(get()) }

}





