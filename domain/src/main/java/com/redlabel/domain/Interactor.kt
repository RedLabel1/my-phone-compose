package com.redlabel.domain

import com.redlabel.ui_common.model.InvokeError
import com.redlabel.ui_common.model.InvokeStarted
import com.redlabel.ui_common.model.InvokeStatus
import com.redlabel.ui_common.model.InvokeSuccess
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

abstract class Interactor<in P, T> {

    companion object {
        private val Timeout = TimeUnit.SECONDS.toMillis(10)
    }

    val flow = MutableSharedFlow<T>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    suspend operator fun invoke(params: P, timeoutMs: Long = Timeout): Flow<InvokeStatus>  = flow {
        try {
            withTimeout(timeoutMs) {
                emit(InvokeStarted)
                kotlinx.coroutines.delay(3000)
                flow.emit(doWork(params))
                emit(InvokeSuccess)
            }
        } catch (t: TimeoutCancellationException) {
            emit(InvokeError(t))
        }
    }.catch { t -> emit(InvokeError(t)) }

    protected abstract suspend fun doWork(params: P): T
}
