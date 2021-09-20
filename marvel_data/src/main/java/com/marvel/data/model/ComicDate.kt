package com.marvel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ComicDate(
    @field:Json(name = "type")  val type: String? = null,
    @field:Json(name = "date")  val date: Date? = null
)