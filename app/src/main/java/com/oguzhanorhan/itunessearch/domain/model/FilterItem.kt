package com.oguzhanorhan.itunessearch.domain.model

class FilterItem(val type: FilterType) {
    val displayName: String
    get() = type.name
}