package com.example.presentation.catlist.adapter

import android.net.Uri
import coil.load
import com.example.databinding.ItemCatBinding

class CatHolder(
    private val itemViewBinding: ItemCatBinding,
) : ViewHolder<CatHolder.DataModel>(itemViewBinding.root) {
    override fun bind(item: DataModel) {
        itemViewBinding.headline.text = item.headline
        itemViewBinding.title.text = item.title
        itemViewBinding.image.load(item.imageUri)
    }

    data class DataModel(
        val title: String,
        val headline: String,
        val imageUri: Uri?,
        override val id: Int,
    ) : EligibleForRecyclerView
}