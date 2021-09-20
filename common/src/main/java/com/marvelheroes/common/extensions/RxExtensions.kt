package com.marvelheroes.common.extensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun Completable.subscribe(
    disposables: CompositeDisposable,
    onComplete: () -> Unit,
    onError: (Throwable) -> Unit = {}
) {
    disposables.add(subscribe(onComplete, onError))
}

fun Completable.prepareForUI(): Completable =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribe(
    disposables: CompositeDisposable,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = {}
) {
    disposables.add(subscribe(onSuccess, onError))
}

fun <T> Single<T>.prepareForUI(): Single<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.subscribe(
    disposables: CompositeDisposable,
    onNext: (T) -> Unit,
    onError: (Throwable) -> Unit = {}
) {
    disposables.add(subscribe(onNext, onError))
}

fun <T> Observable<T>.prepareForUI(): Observable<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())