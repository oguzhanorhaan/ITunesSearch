package com.oguzhanorhan.itunessearch.datasource.model

import android.os.Parcelable
import com.oguzhanorhan.itunessearch.domain.model.ITunesItem
import java.util.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ITunesItemDTO (
    var wrapperType: String? = null,
    var kind: String? = null,
    var artistId: Int? = 0,
    var collectionId: Int? = 0,
    var trackId: Int? = 0,
    var artistName: String? = null,
    var collectionName: String? = null,
    var trackName: String? = null,
    var collectionCensoredName: String? = null,
    var trackCensoredName: String? = null,
    var artistViewUrl: String? = null,
    var collectionViewUrl: String? = null,
    var trackViewUrl: String? = null,
    var previewUrl: String? = null,
    var artworkUrl30: String? = null,
    var artworkUrl60: String? = null,
    var artworkUrl100: String? = null,
    var collectionPrice: Double? = 0.0,
    var trackPrice: Double? = 0.0,
    var releaseDate: String? = null,
    var collectionExplicitness: String? = null,
    var trackExplicitness: String? = null,
    var discCount: Int? = 0,
    var discNumber: Int? = 0,
    var trackCount: Int? = 0,
    var trackNumber: Int? = 0,
    var trackTimeMillis: Int? = 0,
    var country: String? = null,
    var currency: String? = null,
    var primaryGenreName: String? = null,
    var isStreamable: Boolean? = false,
    var collectionArtistId: Int? = 0,
    var collectionArtistViewUrl: String? = null,
    var trackRentalPrice: Double? = 0.0,
    var collectionHdPrice: Double? = 0.0,
    var trackHdPrice: Double? = 0.0,
    var trackHdRentalPrice: Double? = 0.0,
    var contentAdvisoryRating: String? = null,
    var shortDescription: String? = null,
    var longDescription: String? = null,
    var hasITunesExtras: Boolean? = false,
    var collectionArtistName: String? = null,
    ) : Parcelable

fun ITunesItemDTO.mapToDomain(): ITunesItem = ITunesItem(artworkUrl100, collectionPrice, collectionName, releaseDate)
