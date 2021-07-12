package com.lounah.vkmc.core.arch.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import com.lounah.vkmc.core.arch.RxStore
import com.lounah.vkmc.core.arch.util.disposeOnDestroy
import com.lounah.vkmc.core.arch.util.subscribeWhen

/**
 * Привязывает жизненный цикл данного [RxStore] к указанному [lifecycle].
 *
 * @see bind
 */
@Deprecated("use subscribe() and ViewModel scope")
fun <State : Any, UiEvent : Any, News : Any> RxStore<State, UiEvent, News>.bind(
    lifecycle: Lifecycle,
    render: (State) -> Unit,
    handleNews: (News) -> Unit
) {
    subscribe(lifecycle, render, handleNews)
    disposeOnDestroy(lifecycle)
}

/**
 * Создает подписку на `state` и `news`. Подписка выполняется в `onStart`, отписка в `onStop`.
 * Все события перенаправляются на главный поток.
 */
fun <State : Any, UiEvent : Any, News : Any> RxStore<State, UiEvent, News>.subscribe(
    lifecycleOwner: LifecycleOwner,
    render: (State) -> Unit = {},
    handleNews: (News) -> Unit = {}
) {
    subscribe(lifecycleOwner.lifecycle, render, handleNews)
}

/**
 * @see [subscribe]
 */
fun <State : Any, UiEvent : Any, News : Any> RxStore<State, UiEvent, News>.subscribe(
    lifecycle: Lifecycle,
    render: (State) -> Unit = {},
    handleNews: (News) -> Unit = {}
) {
    state.subscribeWhen(lifecycle, STARTED, onNext = render)
    news.subscribeWhen(lifecycle, STARTED, onNext = handleNews)
}