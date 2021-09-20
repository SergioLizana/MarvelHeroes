package com.marvel.di.modules

import com.marvel.data.repository.MarvelRepositoryImpl
import com.marvel.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MarvelRepositoryModule {
    @Binds
    abstract fun bindsMarvelRepository(implementation: MarvelRepositoryImpl): MarvelRepository
}
