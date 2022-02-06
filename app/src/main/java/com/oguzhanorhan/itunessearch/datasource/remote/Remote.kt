package com.oguzhanorhan.itunessearch.datasource.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oguzhanorhan.itunessearch.datasource.model.ITunesItemDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface ItunesApi {
    @GET("search")
    fun search(@Url url : String):
            Deferred<List<ITunesItemDTO>>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


fun createNetworkClient(baseUrl: String) = retrofitClient(baseUrl)

private fun retrofitClient(baseUrl: String): Retrofit =  Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseUrl)
    .build()