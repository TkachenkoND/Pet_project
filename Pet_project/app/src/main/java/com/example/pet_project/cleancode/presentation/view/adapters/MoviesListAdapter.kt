package com.example.pet_project.cleancode.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleancode.domain.models.MovieDetails
import com.example.pet_project.R
import com.example.pet_project.databinding.RecyclerViewMovieItemBinding

interface ClickOnTheItem {
    fun itemPress(movieList: MovieDetails, movieId: Int)
}

class MoviesListAdapter(val clickOnTheItem: ClickOnTheItem) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var films = mutableListOf<MovieDetails>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = RecyclerViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderFilm(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderFilm -> holder.bind(films[position], position)
        }
    }

    override fun getItemCount() = films.size

    inner class ViewHolderFilm(private val binding: RecyclerViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDetails, position: Int) {

            with(binding) {
                Glide.with(filmImage.context)
                    .load("https://www.themoviedb.org/t/p/w220_and_h330_face" + item.filmImage)
                    .error(R.drawable.ic_not_image)
                    .into(filmImage)

                movieTitle.text = item.filmName

                releaseDate.text = item.movieReleaseDate

                filmItem.setOnClickListener {
                    clickOnTheItem.itemPress(item, position)
                }
            }
        }
    }
}