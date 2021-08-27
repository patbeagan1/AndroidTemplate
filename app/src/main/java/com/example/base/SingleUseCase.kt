package com.example.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCase<in INPUT, OUTPUT> constructor(
    private val subscribeOn: Scheduler,
    private val observeOn: Scheduler
) : UseCase() {
    internal abstract fun mapEventToState(params: INPUT): Single<OUTPUT>

    fun partial(params: INPUT) = mapEventToState(params)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)

    fun execute(
        onSuccess: ((t: OUTPUT) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: (() -> Unit) = {},
        params: INPUT,
    ) {
        disposeLast()
        lastDisposable = partial(params)
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)
        lastDisposable?.let { compositeDisposable.add(it) }
    }
}