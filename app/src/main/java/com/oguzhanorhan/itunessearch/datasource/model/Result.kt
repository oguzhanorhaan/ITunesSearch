package com.oguzhanorhan.itunessearch.datasource.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    var resultCount: Int? = 0,
    var results: List<ITunesItemDTO>?
): Parcelable