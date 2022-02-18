package com.patbeagan1.data.util

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Duration
import java.time.Instant

/**
 * Implementation of an in-memory cache
 */
class MemoryCache<T>(
    /**
     * The value that is being stored in the cache
     */
    private var contents: T,
    /**
     * The length of time for which this value is valid.
     * You can use [java.time.Duration] to get a length of time in seconds.
     */
    private val timeToLive: Long = Duration.ofMinutes(1).seconds,
    /**
     * Checks to see if the value is in its initial state.
     */
    private val checkIsEmpty: (T) -> Boolean,
) {

    private val mutex: Mutex = Mutex()
    private var timestamp: Long = Instant.now().epochSecond

    /**
     * Calculates if the cache is due for a refresh.
     */
    fun checkShouldRefresh(isRefreshRequested: Boolean): Boolean {
        val isEmpty = checkIsEmpty(contents)
        val isExpired = (timestamp + timeToLive) < Instant.now().epochSecond
        return isRefreshRequested || isEmpty || isExpired
    }

    /**
     * Sets the specified value as the contents of the cache.
     *
     * Memory safe.
     *
     * @return the value which is being set
     */
    suspend fun setCachedValue(result: T): T = mutex.withLock {
        timestamp = Instant.now().epochSecond
        result.also { contents = it }
    }

    /**
     * Returns the value in the cache.
     *
     * Memory safe.
     *
     * @return the value in this memory cache
     */
    suspend fun getCachedValue(): T = mutex.withLock { contents }
}

/**
 * Keeps a cached value in memory. This is thread-safe.
 *
 * It needs to be an extension so that it can be an inline function.
 * The suspend modifier is not valid on params.
 */
suspend inline fun <T> MemoryCache<T>.invoke(refresh: Boolean = false, operation: () -> T): T =
    if (checkShouldRefresh(refresh)) {
        setCachedValue(operation())
    } else {
        getCachedValue()
    }


