package com.patbeagan1.domain

import com.patbeagan1.di.qualifier.DateNow
import com.patbeagan1.domain.base.AbstractResultUseCase
import com.patbeagan1.domain.entities.EntityDate
import java.util.Date
import javax.inject.Inject

class GetDateUseCase @Inject constructor(
    @DateNow val date: Date
) : AbstractResultUseCase<GetDateUseCase.Params, GetDateUseCase.Results>() {
    class Params
    data class Results(val dateItem: EntityDate)

    override suspend fun doWork(params: Params): Results = Results(
        EntityDate(date)
    )
}
