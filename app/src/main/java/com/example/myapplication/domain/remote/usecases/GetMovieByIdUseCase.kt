package com.example.myapplication.domain.remote.usecases

import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.domain.remote.repositories.MovieRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) {
    suspend fun execute(movieId:Long): Response<MovieResponseById>{
        return movieRemoteRepository.getMovieById(movieId)
    }
}