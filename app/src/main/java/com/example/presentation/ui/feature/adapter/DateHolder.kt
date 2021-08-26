package com.example.presentation.ui.feature.adapter

import com.example.databinding.ItemDateBinding
import com.example.presentation.ui.feature.entities.DateItem

class DateHolder(
    private val dateViewBinding: ItemDateBinding,
) : ViewHolder<DateItem>(dateViewBinding.root) {
    override fun bind(item: DateItem) {
        dateViewBinding.date.text = item.format()
    }
}