package com.patbeagan1.domain

import com.patbeagan1.domain.base.ResultInteractor
import com.patbeagan1.domain.entities.EntityCatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCatUseCase @Inject constructor(
    private val repository: Repository,
) : ResultInteractor<GetCatUseCase.Params, GetCatUseCase.Result>() {
    class Params
    data class Result(val cats: List<EntityCatItem>)

    override suspend fun doWork(params: Params): Result = withContext(Dispatchers.IO) {
        Result(repository.getCats())
    }

    interface Repository {
        suspend fun getCats(): List<EntityCatItem>
    }
}
