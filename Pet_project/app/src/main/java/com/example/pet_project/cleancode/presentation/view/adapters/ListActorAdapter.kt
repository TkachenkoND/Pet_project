package com.example.pet_project.cleancode.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleancode.domain.models.ActorDetails
import com.example.pet_project.R
import com.example.pet_project.databinding.RecyclerViewActorItemBinding

class ListActorAdapter : RecyclerView.Adapter<ListActorAdapter.ViewHolderFilm>() {

    var actors = mutableListOf<ActorDetails>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilm {
        val binding = RecyclerViewActorItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderFilm(binding)
    }

    override fun onBindViewHolder(holder: ListActorAdapter.ViewHolderFilm, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount() = actors.size

    inner class ViewHolderFilm(private val binding: RecyclerViewActorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ActorDetails) {

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