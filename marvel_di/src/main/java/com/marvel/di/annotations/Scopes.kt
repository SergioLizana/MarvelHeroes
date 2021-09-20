package com.marvel.di.annotations

import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class SessionScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ApplicationScope
