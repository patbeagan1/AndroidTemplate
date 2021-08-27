package com.example.presentation.ui.feature.adapter

import com.example.databinding.ItemDateBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateHolder(
    private val dateViewBinding: ItemDateBinding,
) : ViewHolder<DateHolder.DataModel>(dateViewBinding.root) {
    override fun bind(item: DataModel) {
        dateViewBinding.date.text = item.format()
    }

    data class DataModel @Inject constructor(
        private val date: Date,
        override val id: Int
    ) : EligibleForRecyclerView {
        private val simpleDateFormat = SimpleDateFormat("dd MMM")
        fun format(): String = simpleDateFormat.format(date)
    }
}