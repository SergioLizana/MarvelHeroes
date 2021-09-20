package com.test.marvelheroes.di

import com.marvel.di.annotations.ActivityScope
import com.marvel.di.components.BusinessComponent
import com.test.marvelheroes.MarvelHeroMainActivity
import com.test.marvelheroes.view.MarvelHeroDetailFragment
import com.test.marvelheroes.view.MarvelHeroListFragment
import com.test.marvelheroes.view.viewmodel.MarvelViewModel
import dagger.Component

@ActivityScope
@Component(
    dependencies = [BusinessComponent::class]
)
interface MainComponent {

    val marvelViewModel: MarvelViewModel
    fun inject(activity: MarvelHeroMainActivity)
    fun inject(fragment: MarvelHeroListFragment)
    fun inject(fragment: MarvelHeroDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            businessComponent: BusinessComponent
        ): MainComponent
    }
}
