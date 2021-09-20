package com.marvel.data.model

import com.marvel.domain.model.UrlModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Url (
    @field:Json(name = "type")  val type: String? = null,
    @field:Json(name = "url")  val url: String? = null
): DomainMappable<UrlModel> {
    override fun toDomain(): UrlModel  =
        UrlModel(
            type,
            url
        )

}