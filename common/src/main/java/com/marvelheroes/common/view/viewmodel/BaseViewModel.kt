package com.marvelheroes.common.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvelheroes.common.exceptions.*
import com.marvelheroes.common.rx.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    private val mError = SingleLiveEvent<ErrorState>()
    val error: LiveData<ErrorState> = mError

    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    protected fun handleError(error: Throwable) {
        if (error is NetworkException) {
            handleNetworkError(error)
        } else {
            postError(UnexpectedError)
        }
    }

    private fun handleNetworkError(error: NetworkException) {
        when (error) {
            is NoNetworkException -> postError(NoNetworkError)
            is ServerUnreachableException -> postError(ServerUnreachableError)
            is MaxRetriesExceededException -> postError(MaxRetriesExceededError)
            is NoConnectivityException -> postError(NoConnectionAvailableError)
        }
    }

    // Override this method in order to handle the http errors
    protected open fun handleHttpError(error: HttpCallFailureException) {
        postError(UnexpectedError)
    }

    protected fun postError(errorState: ErrorState) {
        mError.postValue(errorState)
    }
}