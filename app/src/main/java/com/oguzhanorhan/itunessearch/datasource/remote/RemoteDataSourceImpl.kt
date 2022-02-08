package com.oguzhanorhan.itunessearch.datasource.remote

import com.oguzhanorhan.itunessearch.data.datasource.RemoteDataSource
import com.oguzhanorhan.itunessearch.datasource.model.SearchResponse

class RemoteDataSourceImpl constructor(
    private val api: ItunesApi
) : RemoteDataSource {

    override suspend fun search(text: String, type: String): SearchResponse {
        return api.search(text, type).await()
    }
}
