package com.marvel.data.model

import com.marvel.domain.model.EventSummaryModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventSummary (
    @field:Json(name = "resourceURI")  val resourceURI: String? = null,
    @field:Json(name = "name")  val name: String? = null
) : DomainMappable<EventSummaryModel> {
    override fun toDomain(): EventSummaryModel =
        EventSummaryModel(resourceURI, name)

}