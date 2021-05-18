package com.network.shared.network

import com.network.shared.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ApiService @Inject constructor(
    private val apiClient: ApiClient,
) {


    fun callMovieListAsync() = apiClient.callMovieListAsync(BuildConfig.apiKey)


}
