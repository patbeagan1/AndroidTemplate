package com.example.domain

import com.example.base.SingleUseCase
import com.example.di.qualifiers.DateNow
import com.example.di.qualifiers.ThreadIO
import com.example.di.qualifiers.ThreadMain
import com.example.domain.entities.DateEntity
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class GetDateUseCase @Inject constructor(
    @ThreadMain val subscribeOn: Scheduler,
    @ThreadIO val observeOn: Scheduler,
    @DateNow val date: Date
) : SingleUseCase<GetDateUseCase.Param, GetDateUseCase.Results>(subscribeOn, observeOn) {
    override fun mapEventToState(params: Param): Single<Results> =
        Single.just(Results(DateEntity(date)))

    class Param
    data class Results(val dateItem: DateEntity)
}

