package com.lounah.vkmc.core.arch

fun <State : Any, UiEvent : Any, News : Any, TargetState : Any> RxStore<State, UiEvent, News>.mapState(
    mapper: (State) -> TargetState
): RxStore<TargetState, UiEvent, News> {
    val store = this
    return object : RxStore<TargetState, UiEvent, News> {
        override val currentState get() = mapper(store.currentState)
        override val state get() = store.state.map(mapper)
        override val news get() = store.news
        override fun dispatch(event: UiEvent) = store.dispatch(event)

        override fun dispose() = store.dispose()
        override fun isDisposed() = store.isDisposed
    }
}