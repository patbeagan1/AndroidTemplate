package com.patbeagan1.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *
 */
abstract class AbstractResultUseCase<in P, R> {
    /**
     * Returns a flow that stands for this operation
     */
    operator fun invoke(params: P): Flow<R> = flow { emit(doWork(params)) }

    /**
     * Performs a synchronous operation and returns the result
     */
    suspend fun executeSync(params: P): R = doWork(params)

    /**
     * Performs an operation and returns the result
     */
    protected abstract suspend fun doWork(params: P): R
}
