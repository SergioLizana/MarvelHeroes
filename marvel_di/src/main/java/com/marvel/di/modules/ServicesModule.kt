package com.marvel.di.modules

import com.marvel.data.datasource.MarvelDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named


@Module
object ServicesModule {

    @JvmStatic
    @Provides
    fun provideMarvelDataSource(@Named("simpleRetrofit") retrofit: Retrofit): MarvelDataSource =
        retrofit.create(MarvelDataSource::class.java)
}