package com.example.presentation.catlist.adapter

import androidx.navigation.findNavController
import com.example.R
import com.example.databinding.ItemDateBinding
import com.example.presentation.catdetail.CatDetailFragment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateHolder(
    private val dateViewBinding: ItemDateBinding,
) : ViewHolder<DateHolder.DataModel>(dateViewBinding.root) {
    override fun bind(item: DataModel) {
        dateViewBinding.date.text = item.format()
        dateViewBinding.root.setOnClickListener {
            it.findNavController().navigate(R.id.action_catFragment_to_catDetailFragment)
        }
    }

    data class DataModel @Inject constructor(
        private val date: Date,
        override val id: Int
    ) : EligibleForRecyclerView {
        private val simpleDateFormat = SimpleDateFormat("dd MMM")
        fun format(): String = simpleDateFormat.format(date)
    }
}