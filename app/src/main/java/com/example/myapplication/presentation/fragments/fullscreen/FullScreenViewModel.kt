package com.example.myapplication.presentation.fragments.fullscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.remote.models.movie.Video
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepository
import com.example.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FullScreenViewModel @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Resource<List<Video>>>()
    val movie: LiveData<Resource<List<Video>>> get() = _movie


    fun getMovieVideoById(movieId: Long) {
        viewModelScope.launch(IO) {
            try {
                _movie.postValue(Resource.Loading)
                val response = movieRemoteRepository.getMovieVideosById(movieId)
                if (response.isSuccessful){
                    response.body()?.results?.let {
                        _movie.postValue(Resource.Success(it))
                    }
                }
            } catch (ex:Exception){
                Log.e("getMovieVideoById","getMovieVideoById method error is: $ex")
            }

        }
    }
}