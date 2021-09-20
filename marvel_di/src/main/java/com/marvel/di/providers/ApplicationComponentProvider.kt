package com.marvel.di.providers

import com.marvel.di.components.ApplicationComponent

interface ApplicationComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}
