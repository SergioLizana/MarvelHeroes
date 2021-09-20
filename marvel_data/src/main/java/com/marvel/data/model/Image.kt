package com.marvel.data.model

import com.marvel.domain.model.ImageModel
import com.marvelheroes.common.model.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image (
    @field:Json(name = "path") var path: String? = null,
    @field:Json(name = "extension") var extension: String? = null
): DomainMappable<ImageModel> {
    override fun toDomain(): ImageModel =
        ImageModel(path,extension)

}