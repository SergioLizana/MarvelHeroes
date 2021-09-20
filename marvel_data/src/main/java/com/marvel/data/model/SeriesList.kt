package com.marvel.data.model

import com.marvel.domain.model.SeriesListModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesList (
    @field:Json(name = "available") var available: Int? = null,
    @field:Json(name = "returned") val returned: Int? = null,
    @field:Json(name = "collectionURI") val collectionURI: String? = null,
    @field:Json(name = "items") val items: List<SeriesSummary>? = null
) : DomainMappable<SeriesListModel> {
    override fun toDomain(): SeriesListModel =
        SeriesListModel(
            available,
            returned,
            collectionURI,
            items?.map {
                it.toDomain()
            }
        )

}