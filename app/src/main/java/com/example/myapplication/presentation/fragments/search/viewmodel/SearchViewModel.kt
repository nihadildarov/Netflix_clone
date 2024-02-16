package com.example.myapplication.presentation.fragments.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository
) : ViewModel() {
    private val _movie = MutableLiveData<List<Result>>()
    val movie: LiveData<List<Result>> get() = _movie


    fun getMovie() {
        viewModelScope.launch(IO) {
                val response = movieRemoteRepository.getPopularMovies()

            try {

                if (response.isSuccessful) {
                    Log.i("RESPONSE_SEARCH","getMovie status is (isSuccessful): ${response.isSuccessful}")
                    response.body()?.let {
                        _movie.postValue(it.results)
                    }
                }
            } catch (ex:Exception){
                Log.e("RESPONSE_SEARCH","getMovie status is: ${response.errorBody()}")
            }
        }
    }

}