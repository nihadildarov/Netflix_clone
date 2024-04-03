package com.example.myapplication.domain.remote.usecases

import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.domain.remote.repositories.MovieRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) {
    suspend fun execute(): Response<MovieResponse>{
        return movieRemoteRepository.getPopularMovies()
    }
}