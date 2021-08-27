package com.example.presentation.ui.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.base.bind
import com.example.databinding.ItemCatBinding
import com.example.databinding.ItemDateBinding
import com.example.presentation.ui.feature.CatRecyclerviewAdapter.ViewType.Cat
import com.example.presentation.ui.feature.CatRecyclerviewAdapter.ViewType.Date
import com.example.presentation.ui.feature.adapter.CatHolder
import com.example.presentation.ui.feature.adapter.DateHolder
import com.example.presentation.ui.feature.adapter.ViewHolder
import com.example.presentation.ui.feature.adapter.EligibleForRecyclerView

class CatRecyclerviewAdapter(
    val data: MutableList<EligibleForRecyclerView> = mutableListOf()
) : ListAdapter<EligibleForRecyclerView, ViewHolder<out EligibleForRecyclerView>>(
    DiffCallback()
) {

    override fun getItemViewType(position: Int): Int = ViewType.from(data[position]).id

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<out EligibleForRecyclerView> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (ViewType[viewType]) {
            Cat -> CatHolder(ItemCatBinding.inflate(layoutInflater, parent, false))
            Date -> DateHolder(ItemDateBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder<out EligibleForRecyclerView>,
        position: Int
    ) {
        val item = data[position]
        when (ViewType[holder.itemViewType]) {
            Cat -> holder.bind<EligibleForRecyclerView, CatHolder.DataModel, CatHolder>(item)
            Date -> holder.bind<EligibleForRecyclerView, DateHolder.DataModel, DateHolder>(item)
        }
    }

    enum class ViewType(val id: Int) {
        Cat(1),
        Date(2);

        companion object {
            operator fun get(viewType: Int): ViewType = values().first { it.id == viewType }
            fun from(eligible: EligibleForRecyclerView) = when (eligible) {
                is CatHolder.DataModel -> Cat
                is DateHolder.DataModel -> Date
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<EligibleForRecyclerView>() {
    override fun areItemsTheSame(
        oldItem: EligibleForRecyclerView,
        newItem: EligibleForRecyclerView
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: EligibleForRecyclerView,
        newItem: EligibleForRecyclerView
    ): Boolean = oldItem == newItem
}