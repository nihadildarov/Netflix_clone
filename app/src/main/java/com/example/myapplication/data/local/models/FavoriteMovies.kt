package com.example.myapplication.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovies(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "movie_id")
    val movieId : Int,

    @ColumnInfo(name = "title")
    val title : Int,

    @ColumnInfo(name = "poster_path")
    val posterPath : String
)
