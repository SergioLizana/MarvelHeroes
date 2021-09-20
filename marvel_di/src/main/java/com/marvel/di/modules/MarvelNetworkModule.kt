package com.marvel.di.modules

import com.marvel.di.annotations.ApplicationScope
import com.marvelheroes.common.BuildConfig
import com.marvelheroes.common.data.RxCallAdapterFactory
import com.marvelheroes.common.data.network.KeyHashInterceptor
import com.marvelheroes.common.data.network.NoConnectionInterceptor
import com.marvelheroes.common.extensions.delegatingCallFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val CALL_TIMEOUT = 30L
@Module
object MarvelNetworkModule {

    @ApplicationScope
    @JvmStatic
    @Provides
    fun providesHTTPClient(
        loggingInterceptor: HttpLoggingInterceptor,
        keyHashInterceptor: KeyHashInterceptor,
        noConnectionInterceptor: NoConnectionInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(keyHashInterceptor)
            addInterceptor(noConnectionInterceptor)
            addInterceptor(loggingInterceptor)
            readTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create(Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build())
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    fun providesCallAdapterFactory(): CallAdapter.Factory {
        return RxCallAdapterFactory.create()
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @JvmStatic
    @Provides
    @Named("baseUrl")
    fun providesBaseURL(): String {
        return BuildConfig.MARVEL_BASE_URL
    }

    @ApplicationScope
    @JvmStatic
    @Provides
    @Named("simpleRetrofit")
    fun providesRetrofit(
        @Named("baseUrl") baseUrl: String,
        httpClient: dagger.Lazy<OkHttpClient>,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .delegatingCallFactory(httpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }

}
