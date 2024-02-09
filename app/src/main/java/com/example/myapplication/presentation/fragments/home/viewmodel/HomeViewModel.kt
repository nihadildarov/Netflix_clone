package com.example.myapplication.presentation.fragments.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) : ViewModel() {
     val popularMovieList = MutableLiveData<List<Result>>()
     val topRatedMovieList = MutableLiveData<List<Result>>()
     val upComingMovieList = MutableLiveData<List<Result>>()


    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpComingMovies()
    }


    private fun getTopRatedMovies() {
        viewModelScope.launch(IO) {
            val response = movieRemoteRepository.getTopRatedMovies()
            try {

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        topRatedMovieList.postValue(body.results)
                    }
                }
            } catch (ex: Exception) {
                Log.e("TOP_RATED_ERROR", response.errorBody().toString())
            }
        }
    }


    private fun getPopularMovies() {
        viewModelScope.launch(IO) {
            val response = movieRemoteRepository.getPopularMovies()
            try {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        popularMovieList.postValue(body.results)
                    }
                }
            } catch (ex: Exception) {
                Log.e("POPULAR_ERROR", response.errorBody().toString())
            }
        }
    }


    private fun getUpComingMovies () {
        viewModelScope.launch(IO) {
            val response = movieRemoteRepository.getUpComingMovies()
            try {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        upComingMovieList.postValue(body.results)
                    }
                }
            } catch (ex: Exception) {
                Log.e("UP_COMING_ERROR", response.errorBody().toString())
            }
        }
    }

}