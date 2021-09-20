package com.marvel.data.model

import com.marvel.domain.model.EventListModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventList (
    @field:Json(name = "available")  val available: Int? = null,
    @field:Json(name = "returned")  val returned: Int? = null,
    @field:Json(name = "collectionURI")  val collectionURI: String? = null,
    @field:Json(name = "items")  val items: List<EventSummary>? = null
) : DomainMappable<EventListModel> {
    override fun toDomain(): EventListModel =
        EventListModel(
            available,
            returned,
            collectionURI,
            items?.map {
                it.toDomain()
            }
        )
}