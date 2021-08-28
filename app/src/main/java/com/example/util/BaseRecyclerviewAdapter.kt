package com.example.util

import com.example.ui.catlist.adapter.ViewHolder


inline fun <reified ELIGIBLE, reified V : ELIGIBLE, reified U : ViewHolder<V>> ViewHolder<out ELIGIBLE>.bind(
    item: ELIGIBLE,
) {
    safeLet(this as? U, item as? V) { safeHolder, safeItem ->
        safeHolder.bind(safeItem)
    }
}
