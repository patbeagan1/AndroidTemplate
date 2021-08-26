package com.example.presentation.ui.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.base.BaseRecyclerviewAdapter
import com.example.databinding.ItemCatBinding
import com.example.databinding.ItemDateBinding
import com.example.presentation.ui.feature.CatRecyclerviewAdapter.ViewType.Cat
import com.example.presentation.ui.feature.CatRecyclerviewAdapter.ViewType.Date
import com.example.presentation.ui.feature.adapter.CatHolder
import com.example.presentation.ui.feature.adapter.DateHolder
import com.example.presentation.ui.feature.adapter.ViewHolder
import com.example.presentation.ui.feature.entities.CatViewItem
import com.example.presentation.ui.feature.entities.DateItem
import com.example.presentation.ui.feature.entities.EligibleForRecyclerView

class CatRecyclerviewAdapter(val data: MutableList<EligibleForRecyclerView>) :
    BaseRecyclerviewAdapter<EligibleForRecyclerView>() {

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
            Cat -> bind<CatViewItem, CatHolder>(item, holder)
            Date -> bind<DateItem, DateHolder>(item, holder)
        }
    }

    enum class ViewType(val id: Int) {
        Cat(1),
        Date(2);

        companion object {
            operator fun get(viewType: Int): ViewType = values().first { it.id == viewType }
            fun from(any: EligibleForRecyclerView) = when (any) {
                is CatViewItem -> Cat
                is DateItem -> Date
            }
        }
    }
}

