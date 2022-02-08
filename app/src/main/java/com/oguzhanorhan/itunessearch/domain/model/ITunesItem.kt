package com.oguzhanorhan.itunessearch.domain.model

import android.os.Parcelable
import com.oguzhanorhan.itunessearch.DateFormat
import com.oguzhanorhan.itunessearch.formatDate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ITunesItem(
    var artworkUrl100: String? = null,
    var collectionPrice: Double? = 0.0,
    var collectionName: String? = null,
    var releaseDate: String? = null,
): Parcelable {
    val formattedDate: String get() = releaseDate?.formatDate(DateFormat.serviceDateFormat, DateFormat.appDateFormat) ?: ""
}