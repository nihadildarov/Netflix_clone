package com.example.myapplication.presentation.fragments.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.remote.models.movie.Result
import com.example.myapplication.data.remote.repositories.movie.MovieRemoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.remote.usecases.GetMovieSearchedUseCase
import com.example.myapplication.domain.remote.usecases.GetPopularMoviesUseCase
import com.example.myapplication.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMovieSearchedUseCase: GetMovieSearchedUseCase,
    private val getPopularMoviesUseCase : GetPopularMoviesUseCase
) : ViewModel() {
    private val _movie = MutableLiveData<Resource<List<Result>>>()
    val movie: LiveData<Resource<List<Result>>> get() = _movie

    private val _searchResultMovie = MutableLiveData<Resource<List<Result>>>()
    val searchResultMovie: LiveData<Resource<List<Result>>> get() = _searchResultMovie


    fun getMovie() {
        viewModelScope.launch(IO) {

            try {
                _movie.postValue(Resource.Loading)
                val response = getPopularMoviesUseCase.execute()
                if (response.isSuccessful) {
                    Log.i("getMovie", "getMovie status is (isSuccessful): ${response.isSuccessful}")
                    response.body()?.let {
                        _movie.postValue(Resource.Success(it.results))
                    }
                }
            } catch (ex: Exception) {
                _movie.postValue(Resource.Error(ex))
                Log.e("getMovie", "getMovie status is: $ex")
            }
        }
    }


//    fun searchMovieByName2(searchKey:String){
//        viewModelScope.launch(IO) {
//            val response = getMovieSearchedUseCase.execute(searchKey)
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

            try {

                _searchResultMovie.postValue(Resource.Loading)
                val response = getPopularMoviesUseCase.execute()
                if (response.isSuccessful) {
                    Log.i("searchMovieByName", "searchMovieByName status is (isSuccessful): ${response.isSuccessful}")
                    response.body()?.let {
                        _searchResultMovie.postValue(Resource.Success(it.results.filter { movieItem ->
                            movieItem.title.lowercase().contains(searchKey.lowercase())
                                    || movieItem.original_title.lowercase()
                                .contains(searchKey.lowercase())
                                    || movieItem.overview.lowercase()
                                .contains(searchKey.lowercase())

                        }))
                    }
                }
            } catch (ex: Exception) {
                _searchResultMovie.postValue(Resource.Error(ex))
                Log.e("searchMovieByName", "searchMovieByName status is: $ex")
            }
        }
    }

}