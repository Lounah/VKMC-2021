package com.lounah.vkmc.core.arch.tea

/**
 * DSL version of [Update].
 *
 * Usage example:
 * ```kotlin
 * class SampleUpdate : DslUpdate<State, Event, Command>() {
 *     override fun NextBuilder.update(event: Event) = when (event) {
 *         UiEvent.OnRefresh -> {
 *             state { copy(value = "loading") }
 *             commands(
 *                 Command.LoadItems.takeIf { initialState.value != "loading" }
 *             )
 *         }
 *         Event.LoadItemsSuccess -> {
 *             state { copy(value = "<some items>") }
 *         }
 *         Event.LoadItemsError -> {
 *             state { copy(value = "failed") }
 *             commands(News("showErrorDialog"))
 *         }
 *     }
 * }
 * ```
 */
abstract class DslUpdate<State : Any, in Event : Any, Command : Any> : Update<State, Event, Command> {
    final override fun update(state: State, event: Event) = NextBuilder(state).apply { update(event) }.build()

    protected abstract fun NextBuilder.update(event: Event)

    // inner class to eliminate generics in update
    protected inner class NextBuilder internal constructor(initialState: State) {
        var state: State = initialState
            private set

        private val commands = mutableListOf<Command>()

        inline fun state(block: State.() -> State) {
            setState(state.block())
        }

        fun commands(vararg commands: Command) {
            this.commands.addAll(commands)
        }

        @PublishedApi // "synthetic" method to call private setter from inline function
        internal fun setState(state: State) {
            this.state = state
        }

        internal fun build(): Next<State, Command> = Next(state, commands)
    }
}