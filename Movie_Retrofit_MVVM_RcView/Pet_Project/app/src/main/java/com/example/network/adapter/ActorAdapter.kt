package com.example.network.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.network.R
import com.example.network.data.TheMovieDbFilmActorList
import com.example.network.data.TheMoviesDBActor
import com.example.network.databinding.RecyclerViewActorItemBinding

class ActorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var actors = mutableListOf<TheMoviesDBActor>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RecyclerViewActorItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderFilm(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ActorAdapter.ViewHolderFilm -> holder.bind(actors[position], position)
        }
    }

    override fun getItemCount() = actors.size

    inner class ViewHolderFilm(private val binding: RecyclerViewActorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TheMoviesDBActor, position: Int) {

            with(binding) {
                Glide.with(actorImage.context)
                    .load("https://www.themoviedb.org/t/p/w220_and_h330_face" + item.actorImage)
                    .error(R.drawable.ic_not_image)
                    .into(actorImage)

                actorName.text = item.actorName

            }
        }
    }
}