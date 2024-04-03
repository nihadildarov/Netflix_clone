package com.example.myapplication.data.remote.repositories.movie.datasource

import com.example.myapplication.data.remote.models.movie.Genre
import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): Response<MovieResponse>
    suspend fun getTopRatedMovies(): Response<MovieResponse>
    suspend fun getUpComingMovies(): Response<MovieResponse>
    suspend fun getMovieById(movieId:Long):Response<MovieResponseById>
    suspend fun getMovieVideosById(movieId: Long):Response<MovieVideoResponseById>
    suspend fun getMovieSearched (query:String):Response<MovieResponse>
    suspend fun getMovieGenres():Response<Genre>
}