package com.lounah.vkmc.core.arch.tea

import java.util.Objects

@Suppress("UseDataClass")
class Next<out State : Any, out Command : Any>(
    val state: State? = null,
    val commands: List<Command> = emptyList(),
    val isValid: Boolean = true
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Next<*, *>

        if (state != other.state) return false
        if (commands != other.commands) return false
        if (isValid != other.isValid) return false

        return true
    }

    override fun hashCode() = Objects.hash(state, commands, isValid)

    override fun toString() = "Next(state=$state, commands=$commands, isValid=$isValid)"
}