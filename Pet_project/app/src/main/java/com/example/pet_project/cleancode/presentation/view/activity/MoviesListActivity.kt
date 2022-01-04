package com.example.pet_project.cleancode.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cleancode.domain.models.MovieDetails
import com.example.pet_project.cleancode.presentation.view.adapters.ClickOnTheItem
import com.example.pet_project.cleancode.presentation.view.adapters.MoviesListAdapter
import com.example.pet_project.cleancode.presentation.viewmodel.MovieListActivityViewModel
import com.example.pet_project.databinding.ActivityMoviesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListActivity : AppCompatActivity(), ClickOnTheItem {

    private val viewModel by viewModel<MovieListActivityViewModel>()

    private val listActorAdapter = MoviesListAdapter(this as ClickOnTheItem)

    lateinit var binding: ActivityMoviesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initObserve()
    }

    private fun initAdapter() {
        binding.recyclerViewContainerFilms.apply {
            adapter = listActorAdapter
        }
    }

    private fun initObserve() {
        viewModel.loadMovieList()

        viewModel.moviesListLiveData.observeForever {
            for (el in it.filmDetails)
                listActorAdapter.films.add(el)

            listActorAdapter.notifyDataSetChanged()
        }
    }

    override fun itemPress(movieList: MovieDetails, movieId: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }
}