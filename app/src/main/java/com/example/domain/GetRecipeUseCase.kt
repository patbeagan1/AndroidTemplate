package com.example.domain

import com.example.base.SingleUseCase
import com.example.di.qualifiers.ThreadIO
import com.example.di.qualifiers.ThreadMain
import com.example.domain.GetCatUseCase.Contract.Repository
import com.example.domain.entities.CatItem
import com.example.domain.entities.DateItem
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetCatUseCase @Inject constructor(
    private val repository: Repository,
    @ThreadIO private val subscribeOn: Scheduler,
    @ThreadMain val observeOn: Scheduler,
    private val dateItem: DateItem
) : SingleUseCase<Pair<DateItem, List<CatItem>>, GetCatUseCase.Params>(
    subscribeOn,
    observeOn
) {
    class Params

    override fun buildUseCaseSingle(params: Params): Single<Pair<DateItem, List<CatItem>>> =
        repository.getCats().flatMap { Single.just(dateItem to it) }

    interface Contract {
        interface Repository {
            fun getCats(): Single<List<CatItem>>
        }
    }
}

