package com.example.pet_project.cleancode.di

import com.example.cleancode.data.repository.DetailsMoviesListRepositoryImpl
import com.example.cleancode.data.repository.MoviesListRepositoryImpl
import com.example.cleancode.data.repository.services.ActorDetailsListServices
import com.example.cleancode.data.repository.services.MovieDetailsListServices
import com.example.cleancode.domain.repository.DetailsMoviesListRepository
import com.example.cleancode.domain.repository.MoviesListRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val OK_HTTP_TIMEOUT = 40L

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideTheMovieDetailsApi(get()) }
    factory { provideTheMoviesListApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val moshiBuilder = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val moshiConverterFactory = MoshiConverterFactory.create(moshiBuilder)

    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(moshiConverterFactory)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
    }.build()
}

fun provideTheMovieDetailsApi(retrofit: Retrofit): ActorDetailsListServices = retrofit.create(
    ActorDetailsListServices::class.java)

fun provideTheMoviesListApi(retrofit: Retrofit): MovieDetailsListServices = retrofit.create(
    MovieDetailsListServices::class.java)

val dataModule = module {
    single<MoviesListRepository> { MoviesListRepositoryImpl(get()) }
    single<DetailsMoviesListRepository> { DetailsMoviesListRepositoryImpl(get()) }

}