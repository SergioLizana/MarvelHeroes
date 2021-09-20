package com.marvel.domain.model

import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class CharacterModel(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var resourceURI: String? = null,
    var urls: List<UrlModel>? = null,
    var thumbnail: ImageModel? = null,
    var comics: ComicListModel? = null,
    var stories: StoryListModel? = null,
    var events: EventListModel? = null,
    var series: SeriesListModel? = null
) : Parcelable