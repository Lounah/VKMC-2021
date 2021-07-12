package com.lounah.vkmc.core.arch.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.Lifecycle.State.DESTROYED
import androidx.lifecycle.Lifecycle.State.INITIALIZED
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.subscribeBy

private val onErrorStub: (Throwable) -> Unit = { RxJavaPlugins.onError(OnErrorNotImplementedException(it)) }

/**
 * Subscribes to this [Observable] on the main thread when [lifecycle] enters specified state and disposes it at exit.
 */
internal fun <T : Any> Observable<T>.subscribeWhen(
    lifecycle: Lifecycle,
    inState: Lifecycle.State,
    onError: (Throwable) -> Unit = onErrorStub,
    onComplete: () -> Unit = {},
    onNext: (T) -> Unit = {}
) {
    var disposable: Disposable? = null
    lifecycle.observeStateBoundary(
        inState,
        onEnter = {
            /* In some cases, lifecycle methods can be called almost sequentially without delay. For example, when
             * calling the Activity#recreate method on a stopped activity in the background, Activity#onStart and
             * Activity#onStop are called immediately. Because of this, the events that are sent when
             * subscribing to Activity#onStart may disappear. Since the Disposable#dispose method is called earlier
             * (because of the Activity#onStop call), the subscribers handle the events.
             * Therefore, we use #postOnUiThread and check the lifecycle state before subscribing.
             */
            postOnUiThread {
                if (lifecycle.currentState.isAtLeast(inState) && disposable == null) {
                    disposable = observeOn(AndroidSchedulers.mainThread()).subscribeBy(onError, onComplete, onNext)
                }
            }
        },
        onExit = {
            disposable?.dispose()
            disposable = null
        }
    )
}

private fun Lifecycle.observeStateBoundary(
    targetState: Lifecycle.State,
    onEnter: () -> Unit,
    onExit: () -> Unit
) {
    check(targetState != DESTROYED) { "Can't observe a boundary of this state" }

    addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when {
                event == ON_CREATE && targetState == CREATED -> onEnter()
                event == ON_START && targetState == STARTED -> onEnter()
                event == ON_RESUME && targetState == RESUMED -> onEnter()
                event == ON_PAUSE && targetState == RESUMED -> onExit()
                event == ON_STOP && targetState == STARTED -> onExit()
                event == ON_DESTROY && (targetState == CREATED || targetState == INITIALIZED) -> onExit()
            }
        }
    })
}