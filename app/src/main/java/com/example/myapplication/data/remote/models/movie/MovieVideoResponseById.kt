package com.example.myapplication.data.remote.models.movie

data class MovieVideoResponseById(
    val id: Int,
    val results: List<Video>
)