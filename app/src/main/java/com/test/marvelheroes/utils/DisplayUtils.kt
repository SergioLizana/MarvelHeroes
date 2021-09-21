package com.test.marvelheroes.utils

import com.marvel.domain.model.CharacterModel
import com.test.marvelheroes.view.model.CharacterDetailsDisplay
import com.test.marvelheroes.view.model.CharacterDisplay

fun CharacterModel.toDisplay(): CharacterDisplay =
    CharacterDisplay(
        characterId = this.id,
        characterName = this.name,
        thumbnail = this.thumbnail
    )

fun CharacterModel.toDetailsDisplay(): CharacterDetailsDisplay =
    CharacterDetailsDisplay(
        characterId = this.id,
        characterName = this.name,
        thumbnail = this.thumbnail,
        comicList = this.comics,
        characterDescription = this.description
    )