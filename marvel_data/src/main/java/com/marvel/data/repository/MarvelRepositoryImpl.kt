package com.marvel.data.repository

import com.marvel.data.datasource.MarvelDataSource
import com.marvel.data.utils.mapNetworkErrors
import com.marvel.data.utils.mapToDomain
import com.marvel.domain.model.CharacterModel
import com.marvel.domain.model.MarvelCharacterModel
import com.marvel.domain.repository.MarvelRepository
import io.reactivex.Single
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
     private val marvelDataSource: MarvelDataSource,
) : MarvelRepository {

    override fun getCharacterList(limit: Int, offset: Int): Single<MarvelCharacterModel> {
        return marvelDataSource.getCharacters(limit = limit,offset = offset)
            .mapNetworkErrors()
            .mapToDomain()

    }

    override fun getCharacter(characterId: Int): Single<MarvelCharacterModel> {
        return marvelDataSource.getCharacter(characterId)
            .mapNetworkErrors()
            .mapToDomain()
    }

}