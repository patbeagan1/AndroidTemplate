package com.example.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase {
    protected var lastDisposable: Disposable? = null
    protected val compositeDisposable = CompositeDisposable()

    fun dispose() = compositeDisposable.clear()

    fun disposeLast() {
        lastDisposable?.takeUnless { it.isDisposed }?.dispose()
    }
}