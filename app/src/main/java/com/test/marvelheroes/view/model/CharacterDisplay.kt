package com.test.marvelheroes.view.model

import com.marvel.domain.model.ImageModel

data class CharacterDisplay(
    val characterId: Int?,
    val characterName: String?,
    val thumbnail: ImageModel?
)
