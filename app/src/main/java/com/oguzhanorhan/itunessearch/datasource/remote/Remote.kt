package com.oguzhanorhan.itunessearch.datasource.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oguzhanorhan.itunessearch.datasource.model.SearchResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {
    @GET("search")
    fun search(
        @Query("term") text: String,
        @Query("media") category: String
    ): Deferred<SearchResponse>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun configureClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}


fun createNetworkClient(baseUrl: String) = retrofitClient(baseUrl)

private fun retrofitClient(baseUrl: String): Retrofit =  Retrofit.Builder()
    .client(configureClient())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseUrl)
    .build()