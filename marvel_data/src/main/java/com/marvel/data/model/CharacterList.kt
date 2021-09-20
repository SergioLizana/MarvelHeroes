package com.marvel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterList(
    @field:Json(name = "available") val available: Int? = null,
    @field:Json(name = "returned") val returned: Int? = null,
    @field:Json(name = "collectionURI") val collectionURI: String? = null,
    @field:Json(name = "items") val items: List<CharacterSummary>? = null
)