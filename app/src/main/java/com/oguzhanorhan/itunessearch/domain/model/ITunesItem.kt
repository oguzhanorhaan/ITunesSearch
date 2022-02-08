package com.oguzhanorhan.itunessearch.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ITunesItem(
    var artworkUrl100: String? = null,
    var collectionPrice: Double? = 0.0,
    var collectionName: String? = null,
    var releaseDate: String? = null,
): Parcelable