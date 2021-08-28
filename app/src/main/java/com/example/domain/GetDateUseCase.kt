package com.example.domain

import com.example.di.qualifiers.DateNow
import com.example.domain.entities.DateEntity
import java.util.*
import javax.inject.Inject

class GetDateUseCase @Inject constructor(
    @DateNow val date: Date
) {

    fun call(): Results = Results(DateEntity(date))


    class Param
    data class Results(val dateItem: DateEntity)
}

