package com.patbeagan1.domain

import com.patbeagan1.domain.base.ResultInteractor
import com.patbeagan1.domain.entities.EntityCatItem
import javax.inject.Inject

class GetSingleCatUseCase @Inject constructor(
    val repository: Repository
) : ResultInteractor<GetSingleCatUseCase.Params, GetSingleCatUseCase.Results>() {
    class Params
    class Results(val data: EntityCatItem)

    override suspend fun doWork(params: Params): Results = Results(repository.getSingleCat())

    interface Repository {
        suspend fun getSingleCat(): EntityCatItem
    }
}
