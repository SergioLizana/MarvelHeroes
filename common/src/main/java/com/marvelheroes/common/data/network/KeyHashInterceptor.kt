package com.marvelheroes.common.data.network

import com.marvelheroes.common.BuildConfig
import com.marvelheroes.common.exceptions.MarvelApiException
import com.marvelheroes.common.utils.AuthHashGenerator
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class KeyHashInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        var hash: String? = null
        val publicKey: String = BuildConfig.PUBLIC_API_KEY_VALUE
        val privateKey: String = BuildConfig.PRIVATE_API_KEY_VALUE
        try {
            hash = AuthHashGenerator().generateHash(timestamp, publicKey, privateKey)
        } catch (e: MarvelApiException) {
            e.printStackTrace()
        }
        var request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter(TIMESTAMP_KEY, timestamp)
            .addQueryParameter(APIKEY_KEY, publicKey)
            .addQueryParameter(HASH_KEY, hash)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        const val TIMESTAMP_KEY = "ts"
        const val HASH_KEY = "hash"
        const val APIKEY_KEY = "apikey"
    }
}
