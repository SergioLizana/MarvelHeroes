package com.marvel.di.components

import com.marvel.di.annotations.SessionScope
import com.marvel.di.modules.MarvelRepositoryModule
import com.marvel.di.modules.ServicesModule
import com.marvel.domain.repository.MarvelRepository
import dagger.Component

@SessionScope
@Component(
    modules = [
        ServicesModule::class,
        MarvelRepositoryModule::class
    ],
    dependencies = [ApplicationComponent::class]
)
interface BusinessComponent {

    val marvelRepository: MarvelRepository

    @Component.Factory
    interface Factory {
        fun create(
            applicationComponent: ApplicationComponent
        ): BusinessComponent
    }
}
