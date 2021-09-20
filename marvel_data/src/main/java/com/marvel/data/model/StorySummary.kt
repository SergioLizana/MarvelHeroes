package com.marvel.data.model

import com.marvel.domain.model.StorySummaryModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StorySummary (
    @field:Json(name = "resourceURI")  val resourceURI: String? = null,
    @field:Json(name = "name")  val name: String? = null,
    @field:Json(name = "type")  val type: String? = null
) : DomainMappable<StorySummaryModel> {
    override fun toDomain(): StorySummaryModel =
        StorySummaryModel(
            resourceURI,
            name,
            type
        )

}