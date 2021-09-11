package com.patbeagan1.ui.catlist.adapter

import android.view.View
import com.patbeagan1.databinding.ItemDateBinding
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DateHolder(
    private val dateViewBinding: ItemDateBinding,
) : ViewHolder<DateHolder.ViewModel>(dateViewBinding.root) {
    override fun bind(item: ViewModel) {
        dateViewBinding.date.text = item.format()
        dateViewBinding.root.setOnClickListener(item.onClick)
    }

    data class ViewModel @Inject constructor(
        private val date: Date,
        override val id: Int,
        val onClick: (View) -> Unit
    ) : EligibleForRecyclerView {
        private val simpleDateFormat = SimpleDateFormat("dd MMM")
        fun format(): String = simpleDateFormat.format(date)
    }
}
