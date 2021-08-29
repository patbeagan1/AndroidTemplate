package com.patbeagan1.ui.catlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

sealed class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

sealed interface EligibleForRecyclerView {
    val id: Int
}
