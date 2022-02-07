package com.oguzhanorhan.itunessearch.domain.usecase

import com.oguzhanorhan.itunessearch.domain.model.FilterItem
import com.oguzhanorhan.itunessearch.domain.model.FilterType

class RetrieveFilterItemsUseCase {

    suspend fun get(): List<FilterItem> {
        return listOf<FilterItem>(
            FilterItem(FilterType.Movies),
            FilterItem(FilterType.Apps),
            FilterItem(FilterType.Music),
            FilterItem(FilterType.Books)
        )
    }
}