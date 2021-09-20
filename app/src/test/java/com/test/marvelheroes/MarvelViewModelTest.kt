package com.test.marvelheroes
import com.marvelheroes.common.test.rules.RxSchedulerRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marvel.domain.model.CharacterDataContainerModel
import com.marvel.domain.model.CharacterModel
import com.marvel.domain.model.MarvelCharacterModel
import com.marvel.domain.repository.MarvelRepository
import com.marvelheroes.common.exceptions.ServerUnreachableException
import com.marvelheroes.common.test.lifecycle.observeOnce
import com.marvelheroes.common.view.viewmodel.ServerUnreachableError
import com.test.marvelheroes.view.viewmodel.MarvelViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MarvelViewModelTest {

    @get:Rule
    val schedulerRule = RxSchedulerRule()

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val repository: MarvelRepository = mockk()
    lateinit var viewmodel: MarvelViewModel

    @Before
    fun setup() {
        viewmodel = MarvelViewModel(repository)
    }

    @Test
    fun `when get character list returns list of CharacterDisplay`() {
        every { repository.getCharacterList(any(),any()) } returns Single.just(
            MarvelCharacterModel(
                code = 1,
                status = "1",
                copyright = null,
                attributionText = null,
                attributionHTML = null,
                etag = null,
                data = CharacterDataContainerModel(
                    results = listOf(
                        CharacterModel(
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
                        CharacterModel(
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
        viewmodel.getMarvelHeroes()
        verify(exactly = 1) { repository.getCharacterList(any(),any()) }
        viewmodel.characterList.observeOnce {
            assert(it.size == 2)
            assert(it[0].characterName == "Spiderman")
        }

    }

    @Test
    fun `when get character list returns error`() {
        every { repository.getCharacterList(any(),any()) } returns Single.error(
            ServerUnreachableException(Throwable())
        )
        viewmodel.getMarvelHeroes()
        verify(exactly = 1) { repository.getCharacterList(any(),any()) }
        viewmodel.error.observeOnce {
           assert(it is ServerUnreachableError)
        }
    }


    @Test
    fun `when get character and returns CharacterDetailDisplay`() {
        every { repository.getCharacter(any()) } returns Single.just(
            MarvelCharacterModel(
                code = 1,
                status = "1",
                copyright = null,
                attributionText = null,
                attributionHTML = null,
                etag = null,
                data = CharacterDataContainerModel(
                    results = listOf(
                        CharacterModel(
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
        viewmodel.getMarvelHero(1)
        verify(exactly = 1) { repository.getCharacter(any()) }
        viewmodel.characterDetail.observeOnce {
            assert(it.characterName == "Spiderman")
        }
    }

    @Test
    fun `when get character returns error`() {
        every { repository.getCharacter(any()) } returns Single.error(
            ServerUnreachableException(Throwable())
        )
        viewmodel.getMarvelHero(1)
        verify(exactly = 1) { repository.getCharacter(any()) }
        viewmodel.error.observeOnce {
            assert(it is ServerUnreachableError)
        }
    }

}