package com.marvel.data

import com.marvel.data.datasource.MarvelDataSource
import com.marvel.data.model.Character
import com.marvel.data.model.CharacterDataContainer
import com.marvel.data.model.MarvelCharacterEntity
import com.marvel.data.repository.MarvelRepositoryImpl
import com.marvel.domain.repository.MarvelRepository
import com.marvelheroes.common.exceptions.HttpCallFailureException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class MarvelRepositoryImplTest {

    private val dataSource: MarvelDataSource = mockk()
    private lateinit var repository: MarvelRepository

    @Before
    fun setUp() {
        repository = MarvelRepositoryImpl(dataSource)
    }

    @Test
    fun `when get character list`() {
        // Given
        every { dataSource.getCharacters(limit = any(), offset = any()) } returns Single.just(
            MarvelCharacterEntity(
                code = 1,
                status = "1",
                copyright = null,
                attributionText = null,
                attributionHTML = null,
                etag = null,
                data = CharacterDataContainer(
                    results = listOf(
                        Character(
                            id = 1,
                            name = "Spiderman",
                            description = "lorem ipsum",
                            resourceURI = "uri",
                            urls = null,
                            thumbnail = null,
                            comics = null,
                            stories = null,
                            events = null,
                            series = null
                        ),
                        Character(
                            id = 1,
                            name = "Spiderman2",
                            description = "lorem ipsum",
                            resourceURI = "uri",
                            urls = null,
                            thumbnail = null,
                            comics = null,
                            stories = null,
                            events = null,
                            series = null
                        )
                    )
                )
            )
        )

        // When
        val test = repository.getCharacterList(1,1).test()

        // Then
        verify(exactly = 1) { dataSource.getCharacters(offset = any(),limit = any()) }
        test.assertValue { it.data?.results?.size  == 2 }
    }

    @Test
    fun `when get character list and throws error`() {
        // Given
        every { dataSource.getCharacters(limit = any(), offset = any()) } returns Single.error(
            HttpCallFailureException(1, Throwable())
        )

        // When
        val test = repository.getCharacterList(1,1).test()

        // Then
        verify(exactly = 1) { dataSource.getCharacters(offset = any(),limit = any()) }
        test.assertError { it is HttpCallFailureException }
    }

    @Test
    fun `when get character and success`() {
        // Given
        every { dataSource.getCharacter(characterId = any()) } returns Single.just(
            MarvelCharacterEntity(
                code = 1,
                status = "1",
                copyright = null,
                attributionText = null,
                attributionHTML = null,
                etag = null,
                data = CharacterDataContainer(
                    results = listOf(
                        Character(
                            id = 1,
                            name = "Spiderman",
                            description = "lorem ipsum",
                            resourceURI = "uri",
                            urls = null,
                            thumbnail = null,
                            comics = null,
                            stories = null,
                            events = null,
                            series = null
                        )
                    )
                )
            )
        )

        // When
        val test = repository.getCharacter(1).test()

        // Then
        verify(exactly = 1) { dataSource.getCharacter(any()) }
        test.assertValue { it.data?.results?.get(0)?.name  == "Spiderman" }
    }

    @Test
    fun `when get character and error`() {
        // Given
        every { dataSource.getCharacter(any()) } returns Single.error(
                HttpCallFailureException(1, Throwable())
                )

        // When
        val test = repository.getCharacter(1).test()

        // Then
        verify(exactly = 1) { dataSource.getCharacter(any()) }
        test.assertError { it is HttpCallFailureException }
    }
}