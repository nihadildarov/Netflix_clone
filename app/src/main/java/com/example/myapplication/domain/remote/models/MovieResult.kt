package com.example.myapplication.domain.remote.models

data class MovieResult(
    val id: Int,
    val title: String,
    val backDropPath: String?,
    val posterPath: String,
    val releaseDate: String,
    val overView: String,
    val genreId: List<Int>,
    val vote: Double
)
