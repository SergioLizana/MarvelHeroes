package com.marvelheroes.common.exceptions

sealed class NetworkException(error: Throwable) : RuntimeException(error)

class NoNetworkException(error: Throwable) : NetworkException(error)

class ServerUnreachableException(error: Throwable) : NetworkException(error)

class MaxRetriesExceededException(error: Throwable) : NetworkException(error)

class HttpCallFailureException(val code: Int, error: Throwable) :
    NetworkException(error)

class NoConnectivityException(error: Throwable) : NetworkException(error)