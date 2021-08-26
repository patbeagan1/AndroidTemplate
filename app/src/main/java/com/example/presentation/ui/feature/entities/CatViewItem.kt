package com.example.presentation.ui.feature.entities

import android.net.Uri

data class CatViewItem(
    val title: String,
    val headline: String,
    val imageUri: Uri?,
) : EligibleForRecyclerView