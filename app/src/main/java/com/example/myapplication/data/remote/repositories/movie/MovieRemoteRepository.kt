package com.example.myapplication.data.remote.repositories.movie

import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import com.example.myapplication.data.util.ApiUtils
import retrofit2.Response

class MovieRemoteRepository {
    private val api by lazy { ApiUtils.instance }

    suspend fun getPopularMovies(): Response<MovieResponse> {
        return api.getPopularMovies()
    }

    suspend fun getTopRatedMovies(): Response<MovieResponse> {
        return api.getTopRatedMovies()
    }

    suspend fun getUpComingMovies(): Response<MovieResponse> {
        return api.getUpComingMovies()
    }

    suspend fun getMovieById(movieId:Long):Response<MovieResponseById>{
        return api.getMovieById(movieId)
    }

    suspend fun getMovieVideosById(movieId: Long):Response<MovieVideoResponseById>{
        return api.getMovieVideosById(movieId)

    }

    suspend fun getMovieSearched (query:String):Response<MovieResponse>{
        return api.getMovieSearched(query)
    }


}