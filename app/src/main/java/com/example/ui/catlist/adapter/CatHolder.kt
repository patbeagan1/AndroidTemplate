package com.example.ui.catlist.adapter

import android.net.Uri
import androidx.navigation.findNavController
import coil.load
import com.example.R
import com.example.databinding.ItemCatBinding

class CatHolder(
    private val itemViewBinding: ItemCatBinding,
) : ViewHolder<CatHolder.ViewModel>(itemViewBinding.root) {
    override fun bind(item: ViewModel) {
        itemViewBinding.headline.text = item.headline
        itemViewBinding.title.text = item.title
        itemViewBinding.image.load(item.imageUri)
        itemViewBinding.root.setOnClickListener {
            it.findNavController().navigate(R.id.action_catFragment_to_composeCatDetailFragment)
        }
    }

    data class ViewModel(
        val title: String,
        val headline: String,
        val imageUri: Uri?,
        override val id: Int,
    ) : EligibleForRecyclerView
}