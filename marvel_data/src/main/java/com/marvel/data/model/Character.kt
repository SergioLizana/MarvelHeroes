package com.marvel.data.model

import com.marvel.domain.model.CharacterModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    @field:Json(name = "id") var id: Int? = null,
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "description") var description: String? = null,
    @field:Json(name = "resourceURI") var resourceURI: String? = null,
    @field:Json(name = "urls") var urls: List<Url>? = null,
    @field:Json(name = "thumbnail") var thumbnail: Image? = null,
    @field:Json(name = "comics") var comics: ComicList? = null,
    @field:Json(name = "stories") var stories: StoryList? = null,
    @field:Json(name = "events") var events: EventList? = null,
    @field:Json(name = "series") var series: SeriesList? = null
) : DomainMappable<CharacterModel> {
    override fun toDomain(): CharacterModel =
        CharacterModel(
            id,
            name,
            description,
            resourceURI,
            urls?.map {
                it.toDomain()
            },
            thumbnail?.toDomain(),
            comics?.toDomain(),
            stories?.toDomain(),
            events?.toDomain(),
            series?.toDomain()
        )

}