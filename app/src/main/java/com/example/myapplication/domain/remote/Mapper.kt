package com.example.myapplication.domain.remote

import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.domain.remote.models.MovieResult

object Mapper {

    fun List<Result>.toMovieResult() = map {
        MovieResult(it.id, it.title, it.backdrop_path, it.poster_path, it.release_date,it.overview,it.genre_ids,it.vote_average)
    }

    fun List<MovieResponse>.toMovieResponse() = map {
        com.example.myapplication.domain.remote.models.MovieResponse(it.results)
    }
}