package com.example.network.client

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val OK_HTTP_TIMEOUT = 40L

class ApiClient {
    private val moshiBuilder = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val moshiConverterFactory = MoshiConverterFactory.create(moshiBuilder)

    private val client = OkHttpClient.Builder().apply {
        connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
    }.build()

    val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(moshiConverterFactory)
        .client(client)
        .build()
}