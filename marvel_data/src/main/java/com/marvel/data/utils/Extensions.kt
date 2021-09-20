package com.marvel.data.utils

import com.marvelheroes.common.model.DomainMappable
import io.reactivex.Single
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T : DomainMappable<R>, R> Single<T>.mapToDomain(): Single<R> = this.map { it.toDomain() }

fun <T> Single<T>.mapNetworkErrors(): Single<T> =
    this.onErrorResumeNext { error ->
        when (error) {
            is SocketTimeoutException -> Single.error(Exception(error))
            is UnknownHostException -> Single.error(Exception(error))
            is HttpException -> Single.error(Exception(error))
            else -> Single.error(error)
        }
    }