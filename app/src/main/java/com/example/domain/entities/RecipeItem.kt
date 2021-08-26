package com.example.domain.entities

import android.net.Uri

data class CatItem(
    val title: String,
    val headline: String,
    val imageUri: Uri?,
)