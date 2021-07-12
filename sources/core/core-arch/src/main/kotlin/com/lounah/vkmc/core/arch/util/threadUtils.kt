package com.lounah.vkmc.core.arch.util

import android.os.Handler
import android.os.Looper

private val mainThreadHandler = Handler(Looper.getMainLooper())

internal fun postOnUiThread(runnable: Runnable) {
    mainThreadHandler.post(runnable)
}
