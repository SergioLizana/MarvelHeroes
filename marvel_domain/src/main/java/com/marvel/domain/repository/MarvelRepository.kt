package com.marvel.domain.repository

import com.marvel.domain.model.CharacterModel
import com.marvel.domain.model.MarvelCharacterModel
import io.reactivex.Single

interface MarvelRepository {
    fun getCharacterList(limit: Int, offset: Int): Single<MarvelCharacterModel>
    fun getCharacter(characterId: Int): Single<MarvelCharacterModel>
}
