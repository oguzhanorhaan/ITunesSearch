package com.oguzhanorhan.itunessearch.domain.repository

import com.oguzhanorhan.itunessearch.data.model.Resource
import com.oguzhanorhan.itunessearch.datasource.model.ITunesItemDTO
import com.oguzhanorhan.itunessearch.domain.model.FilterItem

interface ITunesRepository {
    suspend fun getSearchItemsList(text: String, category: FilterItem): Resource<List<ITunesItemDTO>>
}
