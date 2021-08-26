package com.example.base

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.ui.feature.adapter.ViewHolder
import com.example.util.safeLet

abstract class BaseRecyclerviewAdapter<ELIGIBLE> :
    RecyclerView.Adapter<ViewHolder<out ELIGIBLE>>() {
    inline fun <reified V : ELIGIBLE, reified U : ViewHolder<V>> bind(
        item: ELIGIBLE,
        holder: ViewHolder<out ELIGIBLE>,
    ) {
        safeLet(holder as? U, item as? V) { safeHolder, safeItem ->
            safeHolder.bind(safeItem)
        }
    }
}