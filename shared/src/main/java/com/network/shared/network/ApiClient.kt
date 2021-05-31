package com.network.shared.network
import com.network.shared.domain.video.VideoListRes
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.POST

interface ApiClient {

    @POST("search?term=Michael+jackson&amp;media=musicVideo")
    fun callMovieListAsync(): Deferred<Response<VideoListRes>>

}