package com.marvel.data.model

import com.marvel.domain.model.StoryListModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryList (
    @field:Json(name = "available") var available: Int? = null,
    @field:Json(name = "returned") val returned: Int? = null,
    @field:Json(name = "collectionURI") val collectionURI: String? = null,
    @field:Json(name = "items") val items: List<StorySummary>? = null
) : DomainMappable<StoryListModel> {
    override fun toDomain(): StoryListModel =
        StoryListModel(
            available,
            returned,
            collectionURI,
            items?.map {
                it.toDomain()
            }
        )

}