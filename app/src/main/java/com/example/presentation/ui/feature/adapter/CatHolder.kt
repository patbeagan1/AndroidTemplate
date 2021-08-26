package com.example.presentation.ui.feature.adapter

import coil.load
import com.example.databinding.ItemCatBinding
import com.example.presentation.ui.feature.entities.CatViewItem

class CatHolder(
    private val itemViewBinding: ItemCatBinding,
) : ViewHolder<CatViewItem>(itemViewBinding.root) {
    override fun bind(item: CatViewItem) {
        itemViewBinding.headline.text = item.headline
        itemViewBinding.title.text = item.title
        itemViewBinding.image.load(item.imageUri)
    }
}