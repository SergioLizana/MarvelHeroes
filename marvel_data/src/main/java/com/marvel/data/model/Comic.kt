package com.marvel.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Comic (
    @field:Json(name = "id")  val id: Int? = null,
    @field:Json(name = "digitalId")  val digitalId: Int? = null,
    @field:Json(name = "title")  val title: String? = null,
    @field:Json(name = "issueNumber")  val issueNumber: Double? = null,
    @field:Json(name = "variantDescription")  val variantDescription: String? = null,
    @field:Json(name = "description")  val description: String? = null,
    @field:Json(name = "modified")  val modified: Date? = null,
    @field:Json(name = "isbn")  val isbn: String? = null,
    @field:Json(name = "upc")  val upc: String? = null,
    @field:Json(name = "diamondCode")  val diamondCode: String? = null,
    @field:Json(name = "ean")  val ean: String? = null,
    @field:Json(name = "issn")  val issn: String? = null,
    @field:Json(name = "format")  val format: String? = null,
    @field:Json(name = "pageCount")  val pageCount: Int? = null,
    @field:Json(name = "textObjects")  val textObjects: List<TextObject>? = null,
    @field:Json(name = "resourceURI")  val resourceURI: String? = null,
    @field:Json(name = "urls")  val urls: List<Url>? = null,
    @field:Json(name = "series")  val series: SeriesSummary? = null,
    @field:Json(name = "variants")  val variants: List<ComicSummary>? = null,
    @field:Json(name = "collections")  val collections: List<ComicSummary>? = null,
    @field:Json(name = "collectedIssues")  val collectedIssues: List<ComicSummary>? = null,
    @field:Json(name = "dates")  val dates: List<ComicDate>? = null,
    @field:Json(name = "prices")  val prices: List<ComicPrice>? = null,
    @field:Json(name = "thumbnail")  val thumbnail: Image? = null,
    @field:Json(name = "images")  val images: List<Image>? = null,
    @field:Json(name = "creators")  val creators: CreatorList? = null,
    @field:Json(name = "characters")  val characters: CharacterList? = null,
    @field:Json(name = "stories")  val stories: StoryList? = null,
    @field:Json(name = "events")  val events: EventList? = null
)