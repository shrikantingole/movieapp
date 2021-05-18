package com.network.shared.core.base

import com.google.gson.Gson
import com.network.shared.core.result.Results
import com.network.shared.domain.exception.ErrorMessage
import com.network.shared.rxjava.RxBus
import com.network.shared.util.Constants
import com.network.shared.util.NetworkUtils
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BaseRepository @Inject constructor(
    val network: NetworkUtils
) {
    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Results<T> = try {
        if (!network.hasNetworkConnection()) {
            Results.Error(IOException("Please check your internet connection"))
        } else {
            val response = call.invoke()
            when {
                response.isSuccessful && (response.code() == (Constants.STATUS_SUCCESS) || response.code() == Constants.STATUS_SUCCESS201) -> {
                    Results.Success(response.body()!!)
                }
                response.code() == Constants.INTERNAL_ERROR -> {
                    Results.Error(IOException("Internal Server Error"))
                }
                response.code() == Constants.TOKEN_EXPIRED -> {
                    RxBus.publish(Constants.TOKEN_EXPIRED)
                    Results.Error(IOException("${response.code()}"))
                }
                response.errorBody() != null -> {
                    val message =
                        Gson().fromJson(
                            response.errorBody()?.charStream(),
                            ErrorMessage::class.java
                        )
                    Results.Error(message, response.code())
                }
                else -> {
                    Results.Error(IOException("Something error..."))
                }
            }
        }
    } catch (e: Exception) {
        Results.Error(IOException(errorMessage, e))
    }

    val <T> T.exhaustive: T get() = this
}