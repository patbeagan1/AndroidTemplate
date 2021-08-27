package com.example.base

import com.example.presentation.catlist.adapter.ViewHolder
import com.example.util.safeLet


inline fun <reified ELIGIBLE, reified V : ELIGIBLE, reified U : ViewHolder<V>> ViewHolder<out ELIGIBLE>.bind(
    item: ELIGIBLE,
) {
    safeLet(this as? U, item as? V) { safeHolder, safeItem ->
        safeHolder.bind(safeItem)
    }
}
