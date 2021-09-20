package com.marvel.di.components

import android.content.Context
import com.marvel.di.annotations.ApplicationScope
import com.marvel.di.modules.MarvelNetworkModule
import com.marvel.di.modules.ServicesModule
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

@ApplicationScope
@Component(modules = [MarvelNetworkModule::class])
interface ApplicationComponent {

    val okHttpClient: OkHttpClient
    val converter: Converter.Factory
    val adapter: CallAdapter.Factory
    val context: Context

    @Named("simpleRetrofit")
    fun simpleRetrofit(): Retrofit

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context
        ): ApplicationComponent
    }
}
