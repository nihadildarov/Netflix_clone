package com.example.myapplication.data.remote.repositories.movie
import com.example.myapplication.data.remote.models.movie.Genre
import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import com.example.myapplication.data.remote.repositories.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.example.myapplication.domain.remote.repositories.MovieRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteRepositoryImpl @Inject constructor(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl
) : MovieRemoteRepository {

    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return movieRemoteDataSourceImpl.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): Response<MovieResponse> {
        return movieRemoteDataSourceImpl.getTopRatedMovies()
    }

    override suspend fun getUpComingMovies(): Response<MovieResponse> {
        return movieRemoteDataSourceImpl.getUpComingMovies()
    }

    override suspend fun getMovieById(movieId: Long): Response<MovieResponseById> {
        return movieRemoteDataSourceImpl.getMovieById(movieId)
    }

    override suspend fun getMovieVideosById(movieId: Long): Response<MovieVideoResponseById> {
        return movieRemoteDataSourceImpl.getMovieVideosById(movieId)
    }


    //
//    override suspend fun getMovieSearched (query:String):Response<MovieResponse> {
//        return movieService.getMovieSearched(query)
//    }
    override suspend fun getMovieGenres(): Response<Genre> {
        return movieRemoteDataSourceImpl.getMovieGenres()
    }
}