package com.marvel.data.datasource

import com.marvel.data.model.MarvelCharacterEntity
import com.marvel.domain.model.MarvelCharacterModel
import io.reactivex.Single
import java.util.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelDataSource {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("name") name: String? = null,
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("modifiedSince") modifiedSince: Date? = null,
        @Query("comics") comics: List<Int?>? = null,
        @Query("series") series: List<Int?>? = null,
        @Query("events") events: List<Int?>? = null,
        @Query("stories") stories: List<Int?>? = null,
        @Query("orderBy") orderBy: List<String?>?= null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Single<MarvelCharacterEntity>



    @GET("/v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int?): Single<MarvelCharacterEntity>

}