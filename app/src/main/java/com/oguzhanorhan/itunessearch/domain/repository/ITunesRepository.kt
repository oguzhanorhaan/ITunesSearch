package com.oguzhanorhan.itunessearch.domain.repository

import com.oguzhanorhan.itunessearch.data.model.Resource
import com.oguzhanorhan.itunessearch.datasource.model.ITunesItemDTO

interface ITunesRepository {

    suspend fun getSearchItemsList(): Resource<List<ITunesItemDTO>>
}