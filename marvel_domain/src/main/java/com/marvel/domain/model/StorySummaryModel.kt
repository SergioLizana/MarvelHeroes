package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class StorySummaryModel(
    val resourceURI: String? = null,
    val name: String? = null,
    val type: String? = null
) : Parcelable