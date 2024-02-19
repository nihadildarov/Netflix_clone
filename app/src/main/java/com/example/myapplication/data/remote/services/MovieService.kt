package com.example.myapplication.data.remote.services

import com.example.myapplication.data.remote.models.movie.MovieResponse
import com.example.myapplication.data.remote.models.movie.MovieResponseById
import com.example.myapplication.data.remote.models.movie.MovieVideoResponseById
import com.example.myapplication.util.Constants.ACCESS_TOKEN
import com.example.myapplication.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @Headers(
        "accept:application/json",
        "Authorization: Bearer $ACCESS_TOKEN"
    )
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>


    @Headers(
        "accept:application/json",
        "Authorization: Bearer $ACCESS_TOKEN"
    )
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @Headers(
        "accept:application/json",
        "Authorization: Bearer $ACCESS_TOKEN"
    )
    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        //@Query("language") lang: String = "en-US",
        //@Query("page") page: Int = 1
    ): Response<MovieResponse>

    @Headers(
        "accept:application/json",
        "Authorization: Bearer $ACCESS_TOKEN"
    )
    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") movieId: Long,
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponseById>


    @Headers(
        "accept:application/json",
        "Authorization: Bearer $ACCESS_TOKEN"
    )
    @GET("movie/{id}/videos")
    suspend fun getMovieVideosById(
        @Path("id") movieId: Long,
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieVideoResponseById>


    @Headers(
        "accept:application/json",
        "Authorization: Bearer $ACCESS_TOKEN"
    )
    @GET("search/movie")
    suspend fun getMovieSearched(
        @Query("query") query: String,
        //@Query("include_adult") includeAdult: Boolean = false,
        //@Query("language") lang: String = "en-US",
        //@Query("page") page: Int = 1
    ): Response<MovieResponse>
}