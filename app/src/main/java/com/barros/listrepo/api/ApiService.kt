package com.barros.listrepo.api

import com.barros.listrepo.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val repoAPi : RepoAPI = getRepoApiClient().create(RepoAPI::class.java)

    fun getRepoApiClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Api.BASE_URL)
            .client(getInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getInterceptorClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(CacheInterceptor)
            .build()
    }
}