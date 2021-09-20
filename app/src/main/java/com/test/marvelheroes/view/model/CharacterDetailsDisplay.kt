package com.test.marvelheroes.view.model

import com.marvel.domain.model.ComicListModel
import com.marvel.domain.model.ImageModel

data class CharacterDetailsDisplay(
    val characterId: Int?,
    val characterName: String?,
    val characterDescription: String?,
    val thumbnail: ImageModel?,
    val comicList: ComicListModel?
)
