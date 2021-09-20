package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UrlModel(
    val type: String? = null,
    val url: String? = null
) : Parcelable