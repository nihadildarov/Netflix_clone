package com.example.myapplication.data.local.dao

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.models.FavoriteMovies

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteMovie(movie: FavoriteMovies)


    @Query("Select * From favorite_movies")
    fun getAllFavoriteMovies():LiveData<List<FavoriteMovies>>


    @Delete
    suspend fun deleteFavoriteMovie(movie:FavoriteMovies)

    @Query("Select Exists(Select 1 from favorite_movies where id = :movieId LIMIT 1)")
    suspend fun isMovieIdExists(movieId:Int):Boolean

}