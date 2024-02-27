package com.example.myapplication.presentation.fragments.new_hot.view_model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepository
import com.example.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewHotViewModel @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) : ViewModel() {

    private val _upComing = MutableLiveData<Resource<List<Result>>>()
    val upComing: LiveData<Resource<List<Result>>> get() = _upComing


    private val _everyOneWatching = MutableLiveData<Resource<List<Result>>>()
    val everyOneWatching: LiveData<Resource<List<Result>>> get() = _everyOneWatching


    fun getUpComingMovies() {
        viewModelScope.launch(IO) {
            try {
                _upComing.postValue(Resource.Loading)
                val response = movieRemoteRepository.getUpComingMovies()
                if (response.isSuccessful) {
                    Log.i(
                        "RESPONSES_NEW_HOT",
                        "getUpComingMovies status is (isSuccessful):${response.isSuccessful}"
                    )
                    response.body()?.let {
                        _upComing.postValue(Resource.Success(it.results))
                    }
                }
            } catch (ex: Exception) {
                Log.e("RESPONSES_NEW_HOT", "getUpComingMovies status is : ${Resource.Error(ex)}")
            }
        }
    }



    fun getEveryOneWatchingMovies() {
        viewModelScope.launch(IO) {
            try {
                _everyOneWatching.postValue(Resource.Loading)
                val response = movieRemoteRepository.getPopularMovies()
                if (response.isSuccessful) {
                    Log.i(
                        "RESPONSES_NEW_HOT",
                        "getEveryOneWatchingMovies status is (isSuccessful):${response.isSuccessful}"
                    )
                    response.body()?.let {
                        _everyOneWatching.postValue(Resource.Success(it.results))
                    }
                }
            } catch (ex: Exception) {
                Log.e(
                    "RESPONSES_NEW_HOT",
                    "getEveryOneWatchingMovies status is : ${Resource.Error(ex)}"
                )
            }
        }
    }



}