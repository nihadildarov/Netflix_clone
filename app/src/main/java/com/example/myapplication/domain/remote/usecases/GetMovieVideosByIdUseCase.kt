package com.example.myapplication.domain.remote.usecases

import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import com.example.myapplication.domain.remote.repositories.MovieRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class GetMovieVideosByIdUseCase @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) {
    suspend fun execute(movieId:Long): Response<MovieVideoResponseById>{
        return movieRemoteRepository.getMovieVideosById(movieId)
    }
}