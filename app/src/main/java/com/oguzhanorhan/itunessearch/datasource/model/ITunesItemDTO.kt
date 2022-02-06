package com.oguzhanorhan.itunessearch.datasource.model

import java.util.*

class ITunesItemDTO {
    var wrapperType: String? = null
    var kind: String? = null
    var artistId = 0
    var collectionId = 0
    var trackId = 0
    var artistName: String? = null
    var collectionName: String? = null
    var trackName: String? = null
    var collectionCensoredName: String? = null
    var trackCensoredName: String? = null
    var artistViewUrl: String? = null
    var collectionViewUrl: String? = null
    var trackViewUrl: String? = null
    var previewUrl: String? = null
    var artworkUrl30: String? = null
    var artworkUrl60: String? = null
    var artworkUrl100: String? = null
    var collectionPrice = 0.0
    var trackPrice = 0.0
    var releaseDate: Date? = null
    var collectionExplicitness: String? = null
    var trackExplicitness: String? = null
    var discCount = 0
    var discNumber = 0
    var trackCount = 0
    var trackNumber = 0
    var trackTimeMillis = 0
    var country: String? = null
    var currency: String? = null
    var primaryGenreName: String? = null
    var isStreamable = false
    var collectionArtistId = 0
    var collectionArtistViewUrl: String? = null
    var trackRentalPrice = 0.0
    var collectionHdPrice = 0.0
    var trackHdPrice = 0.0
    var trackHdRentalPrice = 0.0
    var contentAdvisoryRating: String? = null
    var shortDescription: String? = null
    var longDescription: String? = null
    var hasITunesExtras = false
    var collectionArtistName: String? = null
}
