package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ImageModel (
    var path: String? = null,
    var extension: String? = null
): Parcelable