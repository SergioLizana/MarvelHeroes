package com.marvelheroes.common.model

interface DomainMappable<R> {
    fun toDomain(): R
}
