package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.dao.MovieDao
import com.example.myapplication.data.local.db.MovieDatabase
import com.example.myapplication.data.local.repository.MovieLocalRepository
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepository
import com.example.myapplication.data.remote.services.MovieService
import com.example.myapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideMovieDao(movieDb: MovieDatabase): MovieDao {
        return movieDb.movieDao()
    }


    @Provides
    @Singleton
    fun provideLocalRepository(movieDao: MovieDao): MovieLocalRepository {
        return MovieLocalRepository(movieDao)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(movieService: MovieService): MovieRemoteRepository {
        return MovieRemoteRepository(movieService)
    }


    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideMovieService(retrofit:Retrofit):MovieService = retrofit.create(MovieService::class.java)
}