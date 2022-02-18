package com.patbeagan1.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

abstract class AbstractUseCase<in P> {
    suspend fun executeSync(params: P) = doWork(params)
    protected abstract suspend fun doWork(params: P)

    operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InvokeStatus> =
        flow {
            withTimeout(timeoutMs) {
                emit(InvokeStatus.InvokeStarted)
                doWork(params)
                emit(InvokeStatus.InvokeSuccess)
            }
        }.catch { t ->
            emit(InvokeStatus.InvokeError(t))
        }

    sealed class InvokeStatus {
        object InvokeStarted : InvokeStatus()
        object InvokeSuccess : InvokeStatus()
        data class InvokeError(val throwable: Throwable) : InvokeStatus()
    }

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }
}
