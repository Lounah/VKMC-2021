package com.lounah.vkmc.core.arch.tea

fun interface Update<State : Any, in Event : Any, out Command : Any> {

    fun update(state: State, event: Event): Next<State, Command>
}