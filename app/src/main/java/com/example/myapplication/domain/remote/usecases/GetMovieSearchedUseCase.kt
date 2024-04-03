package com.example.myapplication.domain.remote.usecases

import com.example.myapplication.domain.remote.models.MovieResponse
import com.example.myapplication.domain.remote.repositories.MovieRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class GetMovieSearchedUseCase @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) {
//    suspend fun execute(query: String): Response<MovieResponse> {
//        return movieRemoteRepository.getMovieSearched(query)
//    }
}