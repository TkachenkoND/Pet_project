package com.example.network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.network.adapter.ClickOnTheItem
import com.example.network.adapter.FilmsAdapter
import com.example.network.client.ApiClient
import com.example.network.data.TheMovieDbFilmResponse
import com.example.network.databinding.StartActivityBinding
import com.example.network.services.TheMovieDbServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StartActivity : AppCompatActivity(), ClickOnTheItem {

    private lateinit var binding: StartActivityBinding
    private val filmsAdapter = FilmsAdapter(this as ClickOnTheItem)

    private val theMovieDbServices =
        ApiClient().retrofitClient.create(TheMovieDbServices::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StartActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()

        for (el in 1..20)
            theMovieDbServices.getFilm(el).enqueue(object : Callback<TheMovieDbFilmResponse> {
                override fun onResponse(
                    call: Call<TheMovieDbFilmResponse>,
                    response: Response<TheMovieDbFilmResponse>,
                ) {
                    filmsAdapter.films.add(response.body()!!)
                    filmsAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<TheMovieDbFilmResponse>, t: Throwable) {
                    Log.e("MainActivity", "onResponse: ${t.localizedMessage}")
                }
            })

    }

    private fun initAdapter() {
        binding.recyclerViewContainerFilms.apply {
            adapter = filmsAdapter
        }
    }

    override fun itemPress(
        filmId: String,
        filmImage: String,
        overview: String,
        filmName: String,
        movieReleaseDate: String,
        backdropPath: String,
        rating: String,
    ) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("filmId", filmId)
        intent.putExtra("filmImage", filmImage)
        intent.putExtra("overview", overview)
        intent.putExtra("filmName", filmName)
        intent.putExtra("releaseDate", movieReleaseDate)
        intent.putExtra("backdropPath", backdropPath)
        intent.putExtra("rating", rating)

        startActivity(intent)
    }
}