package com.example.network.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.network.R
import com.example.network.data.TheMovieDbFilmResponse
import com.example.network.databinding.RecyclerViewFilmItemBinding

interface ClickOnTheItem {
    fun itemPress(
        filmId: String,
        filmImage: String,
        overview: String,
        filmName: String,
        movieReleaseDate: String,
        backdropPath: String,
        rating: String,
    )
}

class FilmsAdapter(val clickOnTheItem: ClickOnTheItem) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var films = mutableListOf<TheMovieDbFilmResponse>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = RecyclerViewFilmItemBinding.inflate(
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

    inner class ViewHolderFilm(private val binding: RecyclerViewFilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TheMovieDbFilmResponse, position: Int) {

            with(binding) {
                //Встановлюємо постер
                Glide.with(filmImage.context)
                    .load("https://www.themoviedb.org/t/p/w220_and_h330_face" + item.filmDetails[position].filmImage)
                    .error(R.drawable.ic_not_image)
                    .into(filmImage)

                //Встановлюємо назву фільму
                movieTitle.text = item.filmDetails[position].filmName

                //Встановлюємо дату виходу
                releaseDate.text = item.filmDetails[position].movieReleaseDate

                filmItem.setOnClickListener {
                    clickOnTheItem.itemPress(
                        item.filmDetails[position].filmId,
                        item.filmDetails[position].filmImage!!,
                        item.filmDetails[position].overview,
                        item.filmDetails[position].filmName,
                        item.filmDetails[position].movieReleaseDate,
                        item.filmDetails[position].backdropPath!!,
                        item.filmDetails[position].average

                    )
                }
            }

        }
    }
}