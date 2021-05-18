package com.network.shared.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.network.shared.BuildConfig
import com.network.shared.core.base.BaseRepository
import com.network.shared.network.repository.MovieRepository
import com.network.shared.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor, interceptor: SharedInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(Constants.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(Constants.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(Constants.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }


    @Provides
    @Singleton
    fun provideApiClient(builder: Retrofit.Builder): ApiClient {
        return builder
            .baseUrl(BuildConfig.baseUrl)
            .build().create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService, baseRepository: BaseRepository) =
        MovieRepository(apiService, baseRepository)


}
