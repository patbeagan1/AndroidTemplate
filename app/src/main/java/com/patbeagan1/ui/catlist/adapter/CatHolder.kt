package com.patbeagan1.ui.catlist.adapter

import android.net.Uri
import android.view.View
import coil.load
import com.patbeagan1.databinding.ItemCatBinding

class CatHolder(
    private val itemViewBinding: ItemCatBinding,
) : ViewHolder<CatHolder.ViewModel>(itemViewBinding.root) {
    override fun bind(item: ViewModel) {
        itemViewBinding.headline.text = item.headline
        itemViewBinding.title.text = item.title
        itemViewBinding.image.load(item.imageUri)
        itemViewBinding.root.setOnClickListener(item.onClick)
    }

    data class ViewModel constructor(
        val title: String,
        val headline: String,
        val imageUri: Uri?,
        override val id: Int,
        val onClick: (View) -> Unit
    ) : EligibleForRecyclerView
}
