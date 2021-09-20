package com.marvel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicPrice(
    @field:Json(name = "type")  val type: String? = null,
    @field:Json(name = "price")  val price: Float? = null
)