package com.lounah.vkmc.core.arch.util

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Observer

class CachedRelay<T : Any> private constructor() : Relay<T>() {
    companion object {
        fun <T : Any> create(): Relay<T> {
            return CachedRelay()
        }
    }

    private val relay = PublishRelay.create<T>()
    private val cache = mutableListOf<T>()

    @Synchronized
    override fun accept(value: T) {
        if (hasObservers()) {
            relay.accept(value)
        } else {
            cache.add(value)
        }
    }

    override fun hasObservers() = relay.hasObservers()

    @Synchronized
    override fun subscribeActual(observer: Observer<in T>) {
        if (cache.isEmpty()) {
            relay.subscribe(observer)
        } else {
            Observable.fromIterable(cache)
                .doOnComplete { cache.clear() }
                .concatWith(relay)
                .subscribe(observer)
        }
    }
}