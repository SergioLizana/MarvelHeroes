package com.marvelheroes.common.extensions

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit

@PublishedApi
internal inline fun Retrofit.Builder.callFactory(
    crossinline body: (Request) -> Call
) = callFactory { request -> body(request) }

@Suppress("NOTHING_TO_INLINE")
inline fun Retrofit.Builder.delegatingCallFactory(
    delegate: dagger.Lazy<OkHttpClient>
): Retrofit.Builder =
    callFactory {
        delegate.get().newCall(it)
    }