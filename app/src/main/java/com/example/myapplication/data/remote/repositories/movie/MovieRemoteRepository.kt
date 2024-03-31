package com.example.myapplication.data.remote.repositories.movie

import androidx.lifecycle.LiveData
import com.example.myapplication.data.remote.models.movie.Genre
import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import com.example.myapplication.data.remote.services.MovieService
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(
    private val movieService: MovieService
){


    suspend fun getPopularMovies(): Response<MovieResponse> {
        return movieService.getPopularMovies()
    }

    suspend fun getTopRatedMovies(): Response<MovieResponse> {
        return movieService.getTopRatedMovies()
    }

    suspend fun getUpComingMovies(): Response<MovieResponse> {
        return movieService.getUpComingMovies()
    }

    suspend fun getMovieById(movieId:Long):Response<MovieResponseById>{
        return movieService.getMovieById(movieId)
    }

    suspend fun getMovieVideosById(movieId: Long):Response<MovieVideoResponseById>{
        return movieService.getMovieVideosById(movieId)

    }
//
//    suspend fun getMovieSearched (query:String):Response<MovieResponse>{
//        return movieService.getMovieSearched(query)
//    }


    suspend fun getMovieGenres():Response<Genre>{
        return movieService.getMovieGenres()
    }
}