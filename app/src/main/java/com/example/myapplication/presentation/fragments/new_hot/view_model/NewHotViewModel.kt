package com.example.myapplication.presentation.fragments.new_hot.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.models.movie.Genre
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepositoryImpl
import com.example.myapplication.domain.remote.usecases.GetMovieGenresUseCase
import com.example.myapplication.domain.remote.usecases.GetPopularMoviesUseCase
import com.example.myapplication.domain.remote.usecases.GetUpComingMoviesUseCase
import com.example.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewHotViewModel @Inject constructor(
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieGenresUseCase: GetMovieGenresUseCase
) : ViewModel() {

    private val _movieGenres = MutableLiveData<Resource<List<Genre>>>()
    val movieGenres : LiveData<Resource<List<Genre>>>  get() = _movieGenres

    private val _upComing = MutableLiveData<Resource<List<Result>>>()
    val upComing: LiveData<Resource<List<Result>>> get() = _upComing


    private val _everyOneWatching = MutableLiveData<Resource<List<Result>>>()
    val everyOneWatching: LiveData<Resource<List<Result>>> get() = _everyOneWatching


    init {
        getMovieGenres()
    }
    fun getUpComingMovies() {
        viewModelScope.launch(IO) {
            try {
                _upComing.postValue(Resource.Loading)
                val response = getUpComingMoviesUseCase.execute()
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
                val response = getPopularMoviesUseCase.execute()
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

    fun getMovieGenres(){
        viewModelScope.launch(IO) {
            try {
                _movieGenres.postValue(Resource.Loading)
                val response = getMovieGenresUseCase.execute()
                if (response.isSuccessful){
                    Log.i("genres","Success")
                    response.body()?.let {
                        _movieGenres.postValue(Resource.Success(listOf(it)))
                    }
                }
            }catch (ex:Exception){
                Log.i("genres","Error")

                _movieGenres.postValue(Resource.Error(ex))
            }
        }
    }


}