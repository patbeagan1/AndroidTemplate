package com.example.domain

import com.example.di.qualifiers.DateNow
import com.example.domain.entities.EntityDate
import java.util.*
import javax.inject.Inject

class GetDateUseCase @Inject constructor(
    @DateNow val date: Date
) {

    fun call(): Results = Results(EntityDate(date))


    class Param
    data class Results(val dateItem: EntityDate)
}

