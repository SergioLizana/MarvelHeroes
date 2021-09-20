package com.marvel.data.model

import com.marvel.domain.model.SeriesSummaryModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesSummary (
    @field:Json(name = "resourceURI")  val resourceURI: String? = null,
    @field:Json(name = "name")  val name: String? = null
) : DomainMappable<SeriesSummaryModel> {
    override fun toDomain(): SeriesSummaryModel =
        SeriesSummaryModel(
            resourceURI,
            name
        )

}