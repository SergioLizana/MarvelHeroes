package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ComicSummaryModel(
    val resourceURI: String? = null,
    val name: String? = null
) : Parcelable