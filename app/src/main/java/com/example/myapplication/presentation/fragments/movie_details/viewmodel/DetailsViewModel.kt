package com.example.myapplication.presentation.fragments.movie_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.repository.MovieLocalRepository
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.Video
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepositoryImpl
import com.example.myapplication.domain.remote.usecases.GetMovieByIdUseCase
import com.example.myapplication.domain.remote.usecases.GetMovieVideosByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieLocalRepository: MovieLocalRepository,
    private val getMovieVideosByIdUseCase: GetMovieVideosByIdUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel(){
    private val _movieDetail = MutableLiveData<MovieResponseById>()
    val movieDetail : LiveData<MovieResponseById> get() = _movieDetail

    private val _movieVideo = MutableLiveData<List<Video>>()
    val movieVideo : LiveData<List<Video>> get () = _movieVideo

    private val _isMovieExists = MutableLiveData<Boolean>()
    val isMovieExists : LiveData<Boolean> get () = _isMovieExists


    fun getMovieById(movieId: Long){
        viewModelScope.launch(IO) {
            val response = getMovieByIdUseCase.execute(movieId)

            if(response.isSuccessful){
                response.body()?.let {
                    _movieDetail.postValue(it)
                }
            }
        }
    }


    fun getMovieVideoById(movieId: Long){
        viewModelScope.launch(IO) {
            val response = getMovieVideosByIdUseCase.execute(movieId)

            if (response.isSuccessful){
                response.body()?.results?.let {
                    _movieVideo.postValue(it)
                }
            }
        }
    }

    fun isMovieExists (movieId: Long){
        viewModelScope.launch(IO) {
            val result = movieLocalRepository.isMovieExists(movieId)
            _isMovieExists.postValue(result)

        }
    }


}