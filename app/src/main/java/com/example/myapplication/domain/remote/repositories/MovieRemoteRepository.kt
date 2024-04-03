package com.example.myapplication.domain.remote.repositories

import com.example.myapplication.data.remote.models.movie.Genre
import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import dagger.Binds
import dagger.BindsOptionalOf

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Singleton

interface MovieRemoteRepository {

    suspend fun getPopularMovies(): Response<MovieResponse>

    suspend fun getTopRatedMovies(): Response<MovieResponse>

    suspend fun getUpComingMovies(): Response<MovieResponse>

    suspend fun getMovieById(movieId: Long): Response<MovieResponseById>

    suspend fun getMovieVideosById(movieId: Long): Response<MovieVideoResponseById>

    //    suspend fun getMovieSearched (query:String):Response<MovieResponse>
    suspend fun getMovieGenres(): Response<Genre>
}