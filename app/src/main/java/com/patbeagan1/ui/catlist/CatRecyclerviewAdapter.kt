package com.patbeagan1.ui.catlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.patbeagan1.databinding.ItemCatBinding
import com.patbeagan1.databinding.ItemDateBinding
import com.patbeagan1.ui.catlist.ViewType.Cat
import com.patbeagan1.ui.catlist.ViewType.Date
import com.patbeagan1.ui.catlist.adapter.CatHolder
import com.patbeagan1.ui.catlist.adapter.DateHolder
import com.patbeagan1.ui.catlist.adapter.EligibleForRecyclerView
import com.patbeagan1.ui.catlist.adapter.ViewHolder

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
            Cat -> (holder as CatHolder).bind(item as CatHolder.ViewModel)
            Date -> (holder as DateHolder).bind(item as DateHolder.ViewModel)
        }
    }

}

private enum class ViewType(val id: Int) {
    Cat(1),
    Date(2);

    companion object {
        operator fun get(viewType: Int): ViewType = values().first { it.id == viewType }
        fun from(eligible: EligibleForRecyclerView) = when (eligible) {
            is CatHolder.ViewModel -> Cat
            is DateHolder.ViewModel -> Date
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