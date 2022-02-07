package com.oguzhanorhan.itunessearch.data.datasource

import com.oguzhanorhan.itunessearch.datasource.model.SearchResponse

interface RemoteDataSource {

    suspend fun search(text: String, type: String): SearchResponse
}