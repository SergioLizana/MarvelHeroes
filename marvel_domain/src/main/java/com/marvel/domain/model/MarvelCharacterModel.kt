package com.marvel.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class MarvelCharacterModel(
    val code: Int? = null,
    val status: String? = null,
    val copyright: String? = null,
    val attributionText: String? = null,
    val attributionHTML: String? = null,
    val data: CharacterDataContainerModel? = null,
    val etag: String? = null
) : Parcelable