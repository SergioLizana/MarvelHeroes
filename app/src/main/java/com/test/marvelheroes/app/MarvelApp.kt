package com.test.marvelheroes.app

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.marvel.di.components.ApplicationComponent
import com.marvel.di.components.BusinessComponent
import com.marvel.di.components.DaggerApplicationComponent
import com.marvel.di.components.DaggerBusinessComponent
import com.marvel.di.providers.ApplicationComponentProvider
import com.marvel.di.providers.BusinessComponentProvider

class MarvelApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}