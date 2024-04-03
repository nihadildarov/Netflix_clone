package com.example.myapplication.data.remote.repositories.movie.datasourceimpl

import com.example.myapplication.data.remote.models.movie.Genre
import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import com.example.myapplication.data.remote.repositories.movie.datasource.MovieRemoteDataSource
import com.example.myapplication.data.remote.services.MovieService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemoteDataSource{
    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return movieService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): Response<MovieResponse> {
        return movieService.getTopRatedMovies()
    }

    override suspend fun getUpComingMovies(): Response<MovieResponse> {
        return movieService.getUpComingMovies()
    }

    override suspend fun getMovieById(movieId: Long): Response<MovieResponseById> {
        return movieService.getMovieById(movieId)
    }

    override suspend fun getMovieVideosById(movieId: Long): Response<MovieVideoResponseById> {
        return movieService.getMovieVideosById(movieId)
    }

    override suspend fun getMovieSearched(query: String): Response<MovieResponse> {
        return movieService.getMovieSearched(query)
    }

    override suspend fun getMovieGenres(): Response<Genre> {
        return movieService.getMovieGenres()
    }

}