package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class SeriesListModel(
    var available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<SeriesSummaryModel>? = null
) : Parcelable