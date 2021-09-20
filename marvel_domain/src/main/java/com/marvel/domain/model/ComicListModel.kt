package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class ComicListModel(
    var available: Int? = null,
    val returned: Int? = null,
    val collectionURI: String? = null,
    val items: List<ComicSummaryModel>? = null
) : Parcelable