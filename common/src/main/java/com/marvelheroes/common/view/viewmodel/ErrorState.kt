package com.marvelheroes.common.view.viewmodel

interface ErrorState

object UnexpectedError : ErrorState

object NoNetworkError : ErrorState

object ServerUnreachableError : ErrorState

object MaxRetriesExceededError : ErrorState

object NoInternetError : ErrorState

object NoConnectionAvailableError : ErrorState
