package com.oguzhanorhan.itunessearch.domain.usecase

import com.oguzhanorhan.itunessearch.data.model.Resource
import com.oguzhanorhan.itunessearch.datasource.model.ITunesItemDTO
import com.oguzhanorhan.itunessearch.domain.model.FilterItem
import com.oguzhanorhan.itunessearch.domain.repository.ITunesRepository

class SearchItemsUseCase(private val itunesRepository: ITunesRepository) {

    suspend fun get(text: String, category: FilterItem): Resource<List<ITunesItemDTO>> =
        itunesRepository.getSearchItemsList(text, category)
}