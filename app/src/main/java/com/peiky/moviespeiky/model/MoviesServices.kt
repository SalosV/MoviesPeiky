package com.peiky.moviespeiky.model

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServices {

    @GET("3/movie/popular")
    fun listMoviesAsync(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Deferred<MovieResult>

}