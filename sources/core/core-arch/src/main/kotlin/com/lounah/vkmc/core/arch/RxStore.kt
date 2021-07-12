package com.lounah.vkmc.core.arch

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface RxStore<State : Any, in UiEvent : Any, News : Any> : Disposable {
    val currentState: State

    val state: Observable<State>

    val news: Observable<News>

    fun dispatch(event: UiEvent)
}