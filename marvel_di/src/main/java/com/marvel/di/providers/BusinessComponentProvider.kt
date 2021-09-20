package com.marvel.di.providers

import com.marvel.di.components.BusinessComponent

interface BusinessComponentProvider {
    fun provideBusinessComponentComponent(): BusinessComponent
}
