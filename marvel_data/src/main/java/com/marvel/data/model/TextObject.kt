package com.marvel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TextObject (
    @field:Json(name = "type")  val type: String? = null,
    @field:Json(name = "language")  val language: String? = null,
    @field:Json(name = "text")  val text: String? = null
)