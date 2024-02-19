package com.example.myapplication.data.local.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.dao.MovieDao
import com.example.myapplication.data.local.models.FavoriteMovies
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(private val movieDao: MovieDao) {

    fun getAllFavoriteMovies():LiveData<List<FavoriteMovies>>{
        return movieDao.getAllFavoriteMovies()
    }

    suspend fun insertFavoriteMovie(movie: FavoriteMovies){
        return movieDao.insertFavoriteMovie(movie)
    }

    suspend fun deleteFavoriteMovie(movie: FavoriteMovies){
        return movieDao.deleteFavoriteMovie(movie)
    }

    suspend fun isMovieExists(movieId:Long):Boolean{
        return movieDao.isMovieIdExists(movieId)
    }


}