package com.patbeagan1.domain

import com.patbeagan1.domain.base.AbstractResultUseCase
import com.patbeagan1.domain.entities.EntityCatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCatUseCase @Inject constructor(
    private val repository: Repository,
) : AbstractResultUseCase<GetCatUseCase.Params, GetCatUseCase.Result>() {
    data class Params (
        val shouldRefresh: Boolean
    )

    data class Result(val cats: List<EntityCatItem>)

    override suspend fun doWork(params: Params): Result = withContext(Dispatchers.IO) {
        Result(repository.getCats(params.shouldRefresh))
    }

    interface Repository {
        /**
         * @return a list of cats
         */
        suspend fun getCats(refresh: Boolean): List<EntityCatItem>
    }
}
