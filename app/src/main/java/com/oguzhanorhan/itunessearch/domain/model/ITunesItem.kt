package com.oguzhanorhan.itunessearch.domain.model

data class ITunesItem(
    var artworkUrl100: String? = null,
    var collectionPrice: Double? = 0.0,
    var collectionName: String? = null,
    var releaseDate: String? = null,
) {
}