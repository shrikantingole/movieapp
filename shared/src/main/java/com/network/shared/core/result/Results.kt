package com.network.shared.core.result

import com.network.shared.core.result.Results.Success

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Results<out R> {

    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val exception: Exception, val code: Int = 0) : Results<Nothing>()
    object Loading : Results<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception,code=$code,]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Results] is of type [Success] & holds non-null [Success.data].
 */
val Results<*>.succeeded
    get() = this is Success && data != null

fun <T> Results<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}
