package com.marvel.data.model

import com.marvel.domain.model.ComicListModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicList(
    @field:Json(name = "available") var available: Int? = null,
    @field:Json(name = "returned") val returned: Int? = null,
    @field:Json(name = "collectionURI") val collectionURI: String? = null,
    @field:Json(name = "items") val items: List<ComicSummary>? = null,
) : DomainMappable<ComicListModel> {
    override fun toDomain(): ComicListModel =
        ComicListModel(
            available = available,
            returned = returned,
            collectionURI = collectionURI,
            items = items?.map {
                it.toDomain()
            }
        )

}