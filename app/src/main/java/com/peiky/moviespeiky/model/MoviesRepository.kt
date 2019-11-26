package com.peiky.moviespeiky.model

import com.peiky.moviespeiky.BuildConfig
import com.peiky.moviespeiky.ui.util.language

class MoviesRepository {

    suspend fun findMovies(page : Int) =
        RetrofitManager.service
            .listMoviesAsync(
                BuildConfig.API_KEY,
                language(),
                page
            )
            .await()
}