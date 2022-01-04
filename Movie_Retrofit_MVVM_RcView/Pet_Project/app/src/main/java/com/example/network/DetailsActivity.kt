package com.example.network

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.network.adapter.ActorAdapter
import com.example.network.client.ApiClient
import com.example.network.data.TheMovieDbFilmActorList
import com.example.network.databinding.DetailsActivityBinding
import com.example.network.services.TheMovieDbServicesDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityBinding
    private val actorAdapter = ActorAdapter()

    private val theMovieDbServicesDetails =
        ApiClient().retrofitClient.create(TheMovieDbServicesDetails::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailsActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()
        setContent()
    }

    private fun initAdapter() {
        binding.recyclerViewContainerForActors.apply {
            adapter = actorAdapter
        }
    }

    private fun initTheMovieDbServices(id: Int) {
        theMovieDbServicesDetails.getActor(id).enqueue(object : Callback<TheMovieDbFilmActorList> {
            override fun onResponse(
                call: Call<TheMovieDbFilmActorList>,
                response: Response<TheMovieDbFilmActorList>,
            ) {
                for (el in response.body()!!.cast!!)
                    actorAdapter.actors.add(el)
                actorAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<TheMovieDbFilmActorList>, t: Throwable) {
                Log.e("DetailsActivity", "onResponse: ${t.localizedMessage}")
            }
        })
    }

    private fun setContent() {
        with(binding) {
            filmName.text = intent.extras?.getString("filmName")!!.toString()
            overview.text = intent.extras?.getString("overview")!!.toString()
            releaseDate.text = intent.extras?.getString("releaseDate")!!.toString()
            rating.text = intent.extras?.getString("rating")!!.toString() + "/10"
            val actorId = intent.extras?.getString("filmId")!!.toInt()

            Glide.with(filmImage.context)
                .load("https://www.themoviedb.org/t/p/w220_and_h330_face" + intent.extras?.getString(
                    "filmImage")!!.toString())
                .error(R.drawable.ic_not_image)
                .into(filmImage)

            Glide.with(detailsBackground.context)
                .load("https://www.themoviedb.org/t/p/w220_and_h330_face" + intent.extras?.getString(
                    "backdropPath")!!.toString())
                .error(R.drawable.ic_not_image)
                .into(detailsBackground)

            initTheMovieDbServices(actorId)

        }
    }
}