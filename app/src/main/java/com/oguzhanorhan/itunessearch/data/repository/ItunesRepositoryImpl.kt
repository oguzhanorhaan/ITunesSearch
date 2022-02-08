package com.oguzhanorhan.itunessearch.data.repository

import com.oguzhanorhan.itunessearch.data.datasource.RemoteDataSource
import com.oguzhanorhan.itunessearch.data.model.Resource
import com.oguzhanorhan.itunessearch.datasource.model.ITunesItemDTO
import com.oguzhanorhan.itunessearch.datasource.remote.ResponseHandler
import com.oguzhanorhan.itunessearch.domain.model.FilterItem
import com.oguzhanorhan.itunessearch.domain.repository.ITunesRepository

class ItunesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val responseHandler: ResponseHandler
) : ITunesRepository {
    override suspend fun getSearchItemsList(text: String, category: FilterItem): Resource<List<ITunesItemDTO>> {
        return try {
            val response = remoteDataSource.search(text, category.type.key)
            return responseHandler.handleSuccess(response.results)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}
