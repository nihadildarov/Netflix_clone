package com.example.myapplication.domain.remote.models

import com.example.myapplication.data.remote.models.movie.Result

data class MovieResponse (
    val result:List<Result>
)