package com.test.marvelheroes.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvel.domain.repository.MarvelRepository
import com.marvelheroes.common.extensions.*
import com.marvelheroes.common.view.viewmodel.BaseViewModel
import com.marvelheroes.common.view.viewmodel.UnexpectedError
import com.test.marvelheroes.utils.toDetailsDisplay
import com.test.marvelheroes.utils.toDisplay
import com.test.marvelheroes.view.model.CharacterDetailsDisplay
import com.test.marvelheroes.view.model.CharacterDisplay
import javax.inject.Inject

class MarvelViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository
) : BaseViewModel() {

    private val _characterList = MutableLiveData<List<CharacterDisplay>>()
    val characterList: LiveData<List<CharacterDisplay>> = _characterList

    private val _characterDetail = MutableLiveData<CharacterDetailsDisplay>()
    val characterDetail: LiveData<CharacterDetailsDisplay> = _characterDetail

    fun getMarvelHeroes() {
        marvelRepository.getCharacterList(10, 10)
            .doOnSubscribe { _loading.postValue(true) }
            .prepareForUI()
            .subscribe(
                disposables = disposables,
                onSuccess = {
                    var characterDisplayList: List<CharacterDisplay>? = it.data?.results?.map { characterModel ->
                        characterModel.toDisplay()
                    }
                    characterDisplayList?.let { list ->
                        _characterList.postValue(list)
                    }?: kotlin.run {
                        postError(UnexpectedError)
                    }
                },
                onError = ::handleError
            )
    }

    fun getMarvelHero(marvelId: Int) {
        marvelRepository.getCharacter(marvelId)
            .doOnSubscribe { _loading.postValue(true) }
            .prepareForUI()
            .subscribe(
                disposables = disposables,
                onSuccess = {
                    var characterDetailsDisplay: CharacterDetailsDisplay? = it.data?.results?.get(0)?.let { characterModel ->
                        characterModel.toDetailsDisplay()
                    }
                    characterDetailsDisplay?.let { characterDetail ->
                        _characterDetail.postValue(characterDetail)
                    }?: kotlin.run {
                        postError(UnexpectedError)
                    }
                },
                onError = ::handleError
            )
    }

}