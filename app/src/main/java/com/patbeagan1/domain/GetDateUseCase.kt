package com.patbeagan1.domain

import com.patbeagan1.di.qualifiers.DateNow
import com.patbeagan1.domain.entities.EntityDate
import java.util.*
import javax.inject.Inject

class GetDateUseCase @Inject constructor(
    @DateNow val date: Date
) {

    fun call(): Results = Results(EntityDate(date))


    data class Results(val dateItem: EntityDate)
}
