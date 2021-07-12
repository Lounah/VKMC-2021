package com.lounah.vkmc.core.arch.tea

import io.reactivex.Observable

fun interface CommandsHandler<Command : Any, out Event : Any> {

    fun handle(commands: Observable<Command>): Observable<out Event>
}