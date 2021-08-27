package com.example.domain.entities

import android.net.Uri

data class CatItemEntity(
    val title: String,
    val headline: String,
    val imageUri: Uri?,
)