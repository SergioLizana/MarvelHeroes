package com.marvel.data.model

import com.marvel.domain.model.ComicSummaryModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicSummary(
    @field:Json(name = "resourceURI")  val resourceURI: String? = null,
    @field:Json(name = "name")  val name: String? = null
) : DomainMappable<ComicSummaryModel> {
    override fun toDomain(): ComicSummaryModel =
        ComicSummaryModel(
            resourceURI = resourceURI,
            name = name
        )

}
