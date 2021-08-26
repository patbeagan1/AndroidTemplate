package com.example.presentation.ui.feature.entities

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

data class DateItem(private val date: Date) : EligibleForRecyclerView {
    @Inject
    lateinit var simpleDateFormat: SimpleDateFormat
    fun format(): String = simpleDateFormat.format(date)
}

