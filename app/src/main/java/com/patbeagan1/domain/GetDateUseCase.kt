package com.patbeagan1.domain

import com.patbeagan1.di.qualifiers.DateNow
import com.patbeagan1.domain.base.ResultInteractor
import com.patbeagan1.domain.entities.EntityDate
import java.util.Date
import javax.inject.Inject

class GetDateUseCase @Inject constructor(
    @DateNow val date: Date
) : ResultInteractor<GetDateUseCase.Params, GetDateUseCase.Results>() {
    class Params
    data class Results(val dateItem: EntityDate)

    override suspend fun doWork(params: Params): Results = Results(
        EntityDate(date)
    )
}
