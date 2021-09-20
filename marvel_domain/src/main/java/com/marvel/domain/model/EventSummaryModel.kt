package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Keep
@Parcelize
data class EventSummaryModel(
    val resourceURI: String? = null,
    val name: String? = null
) : Parcelable