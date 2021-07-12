package com.lounah.vkmc.core.arch.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.Disposable

/**
 * Schedule disposal of this [Disposable] during next onPause event.
 */
fun Disposable.disposeOnPause(lifecycle: Lifecycle) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onPause(owner: LifecycleOwner) {
            dispose()
            lifecycle.removeObserver(this)
        }
    })
}

/**
 * Schedule disposal of this [Disposable] during next onStop event.
 */
fun Disposable.disposeOnStop(lifecycle: Lifecycle) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onStop(owner: LifecycleOwner) {
            dispose()
            lifecycle.removeObserver(this)
        }
    })
}

/**
 * Schedule disposal of this [Disposable] during next onDestroy event.
 */
fun Disposable.disposeOnDestroy(lifecycle: Lifecycle) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            dispose()
            lifecycle.removeObserver(this)
        }
    })
}

/**
 * Schedule disposal of this [Disposable] during next onPause event.
 */
fun Disposable.disposeOnPause(lifecycleOwner: LifecycleOwner) = disposeOnPause(lifecycleOwner.lifecycle)

/**
 * Schedule disposal of this [Disposable] during next onStop event.
 */
fun Disposable.disposeOnStop(lifecycleOwner: LifecycleOwner) = disposeOnStop(lifecycleOwner.lifecycle)

/**
 * Schedule disposal of this [Disposable] during next onDestroy event.
 */
fun Disposable.disposeOnDestroy(lifecycleOwner: LifecycleOwner) = disposeOnDestroy(lifecycleOwner.lifecycle)

/**
 * Schedule disposal of this [Disposable] during next onDestroyView event.
 */
fun Disposable.disposeOnDestroyView(fragment: Fragment) = disposeOnDestroy(fragment.viewLifecycleOwner)