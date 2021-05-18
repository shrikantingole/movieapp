package com.network.shared.network.repository

import com.network.shared.core.base.BaseRepository
import com.network.shared.network.ApiService
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val service: ApiService,
    private val baseRepository: BaseRepository
) {

    suspend fun callMovieListAsync() = baseRepository.safeApiCall(
        call = {
            service.callMovieListAsync().await()
        },
        errorMessage = "Error occurred"
    )

}
