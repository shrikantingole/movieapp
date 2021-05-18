package com.network.shared.network

import com.network.shared.domain.exception.movie.MovieListRes
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {

    @POST("movie/popular")
    fun callMovieListAsync(@Query("api_key") apiKey: String): Deferred<Response<MovieListRes>>

}