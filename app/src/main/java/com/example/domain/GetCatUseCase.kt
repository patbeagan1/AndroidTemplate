package com.example.domain

import com.example.base.SingleUseCase
import com.example.di.qualifiers.ThreadIO
import com.example.di.qualifiers.ThreadMain
import com.example.domain.entities.CatItemEntity
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetCatUseCase @Inject constructor(
    private val repository: Repository,
    @ThreadIO private val subscribeOn: Scheduler,
    @ThreadMain val observeOn: Scheduler
) : SingleUseCase<GetCatUseCase.Params, GetCatUseCase.Result>(
    subscribeOn,
    observeOn
) {
    class Params
    data class Result (val cats: List<CatItemEntity>)

    override fun mapEventToState(params: Params): Single<Result> =
        repository.getCats().flatMap {
            Single.just(Result(it))
        }

    interface Repository {
        fun getCats(): Single<List<CatItemEntity>>
    }
}

