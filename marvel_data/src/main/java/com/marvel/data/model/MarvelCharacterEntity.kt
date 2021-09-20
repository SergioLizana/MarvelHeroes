package com.marvel.data.model

import com.marvel.domain.model.MarvelCharacterModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json


data class MarvelCharacterEntity(
    @field:Json(name = "code") val code: Int? = null,
    @field:Json(name = "status") val status: String? = null,
    @field:Json(name = "copyright") val copyright: String? = null,
    @field:Json(name = "attributionText") val attributionText: String? = null,
    @field:Json(name = "attributionHTML") val attributionHTML: String? = null,
    @field:Json(name = "data") val data: CharacterDataContainer? = null,
    @field:Json(name = "etag") val etag: String? = null
) : DomainMappable<MarvelCharacterModel> {
    override fun toDomain(): MarvelCharacterModel =
        MarvelCharacterModel(
            code = code,
            status = status,
            copyright = copyright,
            attributionHTML = attributionHTML,
            data = data?.toDomain(),
            etag = etag,
        )

}