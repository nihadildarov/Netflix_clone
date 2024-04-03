package com.example.myapplication.presentation.fragments.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepositoryImpl
import com.example.myapplication.domain.remote.usecases.GetPopularMoviesUseCase
import com.example.myapplication.domain.remote.usecases.GetTopRatedMoviesUseCase
import com.example.myapplication.domain.remote.usecases.GetUpComingMoviesUseCase
import com.example.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

) : ViewModel() {
    private val _popularMovieList = MutableLiveData<Resource<List<Result>>>()
    val popularMovieList: LiveData<Resource<List<Result>>> get() = _popularMovieList

    private val _topRatedMovieList = MutableLiveData<Resource<List<Result>>>()
    val topRatedMovieList: LiveData<Resource<List<Result>>> get() = _topRatedMovieList

    private val _upComingMovieList = MutableLiveData<Resource<List<Result>>>()
    val upComingMovieList: LiveData<Resource<List<Result>>> get() = _upComingMovieList


    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpComingMovies()
    }


    private fun getTopRatedMovies() {
        viewModelScope.launch(IO) {
            try {
                _topRatedMovieList.postValue(Resource.Loading)
                val response = getTopRatedMoviesUseCase.execute()

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        _topRatedMovieList.postValue(Resource.Success(it.results))
                    }
                }

            } catch (ex: Exception) {
                _topRatedMovieList.postValue(Resource.Error(ex))
                Log.e("RESPONSES_HOME", "getTopRated status : $ex")
            }
        }
    }


    private fun getPopularMovies() {
        viewModelScope.launch(IO) {
            try {
                _popularMovieList.postValue(Resource.Loading)
                val response = getPopularMoviesUseCase.execute()
                if (response.isSuccessful) {
                    Log.i(
                        "RESPONSES_HOME",
                        "getPopularMovies status is (isSuccessful):${response.isSuccessful}"
                    )
                    val body = response.body()
                    body?.let {
                        _popularMovieList.postValue(Resource.Success(it.results))
                    }
                }
            } catch (ex: Exception) {
                _popularMovieList.postValue(Resource.Error(ex))
                Log.e(
                    "RESPONSES_HOME",
                    "getPopularMovies status : $ex"
                )
            }
        }
    }


    private fun getUpComingMovies() {
        viewModelScope.launch(IO) {
            try {
                _upComingMovieList.postValue(Resource.Loading)
                val response = getUpComingMoviesUseCase.execute()
                if (response.isSuccessful) {
                    Log.i(
                        "RESPONSES_HOME",
                        "getUpComingMovies status is (isSuccessful):${response.isSuccessful}"
                    )
                    val body = response.body()
                    body?.let {
                        _upComingMovieList.postValue(Resource.Success(it.results))
                    }
                }
            } catch (ex: Exception) {
                _upComingMovieList.postValue(Resource.Error(ex))
                Log.e(
                    "RESPONSES_HOME",
                    "getUpComingMovies status is (isSuccessful):$ex"
                )
            }
        }
    }

}