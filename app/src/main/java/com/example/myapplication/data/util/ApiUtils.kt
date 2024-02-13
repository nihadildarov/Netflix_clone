package com.example.myapplication.data.util

import com.example.myapplication.data.remote.services.MovieService

object ApiUtils {
    val instance: MovieService by lazy {RetrofitClient.getInstance().create(MovieService::class.java)}
}