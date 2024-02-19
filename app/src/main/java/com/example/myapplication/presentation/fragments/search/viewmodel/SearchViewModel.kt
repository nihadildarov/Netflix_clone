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

    private val _searchResultMovie = MutableLiveData<List<Result>>()
    val searchResultMovie : LiveData<List<Result>> get() = _searchResultMovie


    fun getMovie() {
        viewModelScope.launch(IO) {
                val response = movieRemoteRepository.getPopularMovies()

            try {

                if (response.isSuccessful) {
                    Log.i("getMovie","getMovie status is (isSuccessful): ${response.isSuccessful}")
                    response.body()?.let {
                        _movie.postValue(it.results)
                    }
                }
            } catch (ex:Exception){
                Log.e("getMovie","getMovie status is: ${response.errorBody()}")
            }
        }
    }


//    fun searchMovieByName2(searchKey:String){
//        viewModelScope.launch(IO) {
//            val response = movieRemoteRepository.getMovieSearched(searchKey)
//
//            try {
//                if (response.isSuccessful){
//                    Log.i("searchMovieByName","searchMovieByName status is (isSuccessful): ${response.isSuccessful}")
//                    response.body()?.let {
//                        _searchResultMovie.postValue(it.results.filter { it.backdrop_path!=null && it.video != false  })
//
//                    }
//
//                }
//            } catch (ex:Exception){
//                Log.e("searchMovieByName","searchMovieByName status is: ${response.errorBody()}")
//            }
//        }
//    }


    fun searchMovieByName(searchKey: String) {
        viewModelScope.launch(IO) {
            val response = movieRemoteRepository.getPopularMovies()

            try {

                if (response.isSuccessful) {
                    Log.i("getMovie","getMovie status is (isSuccessful): ${response.isSuccessful}")
                    response.body()?.let {
                        _searchResultMovie.postValue(it.results.filter { movieItem ->
                            movieItem.title.lowercase().contains(searchKey.lowercase())
                                    || movieItem.original_title.lowercase().contains(searchKey.lowercase())
                                    || movieItem.overview.lowercase().contains(searchKey.lowercase())

                        })
                    }
                }
            } catch (ex:Exception){
                Log.e("getMovie","getMovie status is: ${response.errorBody()}")
            }
        }
    }

}