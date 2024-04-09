package com.example.myapplication.presentation.fragments.home.model

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.remote.models.MovieResult

data class ChildRcyModel(
    val header: String,
    val recycler : List<MovieResult>
)
