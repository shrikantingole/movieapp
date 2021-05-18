package com.network.shared.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SharedInterceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder().build()
        val request = original.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("api_key", "434d1d4ec64f574aed3d6f31bc984c2f")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .url(url).build()

        return chain.proceed(request)

    }
}