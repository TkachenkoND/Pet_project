package com.example.pet_project.cleancode.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pet_project.R
import com.example.pet_project.cleancode.presentation.view.adapters.ListActorAdapter
import com.example.pet_project.cleancode.presentation.viewmodel.MovieDetailsActivityViewModel
import com.example.pet_project.cleancode.presentation.viewmodel.MovieListActivityViewModel
import com.example.pet_project.databinding.ActivityMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity() : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding

    private val detailVm by viewModel<MovieDetailsActivityViewModel>()
    private val listVm by viewModel<MovieListActivityViewModel>()

    private val listActorAdapter = ListActorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMovieDetail()

        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerViewContainerForActors.apply {
            adapter = listActorAdapter
        }
    }

    private fun setMovieDetail() {

        val movieId = getMovieId()
        var id: Int

        listVm.loadMovieList()

        listVm.moviesListLiveData.observe(this, Observer {
            with(binding) {
                filmName.text = it.filmDetails[movieId].filmName
                overview.text = it.filmDetails[movieId].overview
                releaseDate.text = it.filmDetails[movieId].movieReleaseDate
                rating.text = it.filmDetails[movieId].rating + "/10"
                id = it.filmDetails[movieId].filmId.toInt()

                Glide.with(filmImage.context)
                    .load("https://www.themoviedb.org/t/p/w220_and_h330_face" + it.filmDetails[movieId].filmImage)
                    .error(R.drawable.ic_not_image)
                    .into(filmImage)

                Glide.with(detailsBackground.context)
                    .load("https://www.themoviedb.org/t/p/w220_and_h330_face" + it.filmDetails[movieId].backdropPath)
                    .error(R.drawable.ic_not_image)
                    .into(detailsBackground)
            }

            setMovieActorList(id)
        })
    }

    private fun setMovieActorList(movieId: Int) {

        detailVm.loadMovieDetail(movieId)

        detailVm.moviesDetail.observe(this, Observer {
            for (el in it.cast!!)
                listActorAdapter.actors.add(el)

            listActorAdapter.notifyDataSetChanged()
        })
    }

    private fun getMovieId() = intent.extras?.getInt("movieId")!!.toInt()
}