package com.marvel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Series (
    @field:Json(name = "id") val id: Int? = null,
    @field:Json(name = "title") val title: String? = null,
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "resourceURI") val resourceURI: String? = null,
    @field:Json(name = "urls") val urls: List<Url>? = null,
    @field:Json(name = "startYear") val startYear: Int? = null,
    @field:Json(name = "endYear") val endYear: Int? = null,
    @field:Json(name = "rating") val rating: String? = null,
    @field:Json(name = "modified") val modified: Date? = null,
    @field:Json(name = "thumbnail") val thumbnail: Image? = null,
    @field:Json(name = "comics") val comics: ComicList? = null,
    @field:Json(name = "stories") val stories: StoryList? = null,
    @field:Json(name = "events") val events: EventList? = null,
    @field:Json(name = "characters") val characters: CharacterList? = null,
    @field:Json(name = "creators") val creators: CreatorList? = null,
    @field:Json(name = "next") val next: SeriesSummary? = null,
    @field:Json(name = "previous") val previous: SeriesSummary? = null
)