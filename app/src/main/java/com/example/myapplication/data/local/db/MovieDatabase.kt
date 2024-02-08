package com.example.myapplication.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.MovieDao
import com.example.myapplication.data.local.models.FavoriteMovies

@Database(entities = [FavoriteMovies::class], version = 1)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun movieDao(): MovieDao

}