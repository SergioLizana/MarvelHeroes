package com.test.marvelheroes

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.marvel.di.components.ApplicationComponent
import com.marvel.di.components.BusinessComponent
import com.marvel.di.components.DaggerApplicationComponent
import com.marvel.di.components.DaggerBusinessComponent
import com.marvel.di.providers.ApplicationComponentProvider
import com.marvel.di.providers.BusinessComponentProvider
import com.test.marvelheroes.databinding.ActivityMainBinding
import com.test.marvelheroes.di.DaggerMainComponent
import com.test.marvelheroes.di.MainComponent
import com.test.marvelheroes.di.MainInjection

class MarvelHeroMainActivity : AppCompatActivity(), ApplicationComponentProvider,
    BusinessComponentProvider, MainInjection {

    private lateinit var appComponent: ApplicationComponent
    private lateinit var businessComponent: BusinessComponent
    private lateinit var mainComponent: MainComponent

    override fun provideApplicationComponent(): ApplicationComponent = appComponent
    override fun provideBusinessComponentComponent(): BusinessComponent = businessComponent

    private var _binding: ActivityMainBinding? = null
    private var binding: ActivityMainBinding
        get() = _binding
            ?: throw IllegalStateException("Binding is null, check the status of your Activity ${this::class}")
        set(value) {
            _binding = value
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MarvelHeroes)
        _binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        appComponent = DaggerApplicationComponent
            .factory()
            .create(
                applicationContext
            )
        businessComponent = DaggerBusinessComponent
            .factory()
            .create(appComponent)

        mainComponent = DaggerMainComponent
            .factory()
            .create(businessComponent)

        mainComponent.inject(this)

    }

    override fun getMainComponent(): MainComponent = mainComponent

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}