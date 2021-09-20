package com.marvel.data.model

import com.marvel.domain.model.CharacterDataContainerModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataContainer(
    @field:Json(name = "offset") val offset: Int? = null,
    @field:Json(name = "limit") val limit: Int? = null,
    @field:Json(name = "total") val total: Int? = null,
    @field:Json(name = "count") val count: Int? = null,
    @field:Json(name = "results") var results: List<Character>? = null
): DomainMappable<CharacterDataContainerModel> {
    override fun toDomain(): CharacterDataContainerModel =
        CharacterDataContainerModel(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            results = results?.map {
                it.toDomain()
            }
        )

}