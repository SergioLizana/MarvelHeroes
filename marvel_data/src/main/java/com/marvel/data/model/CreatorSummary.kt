package com.marvel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatorSummary (
    @field:Json(name = "resourceURI")  val resourceURI: String? = null,
    @field:Json(name = "name")  val name: String? = null,
    @field:Json(name = "role")  val role: String? = null
)