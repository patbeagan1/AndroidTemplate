package com.patbeagan1.data.network.catapi.response

import com.google.gson.annotations.SerializedName
import java.util.*

class CatResponse : ArrayList<CatItem>()

data class CatItem(
    @SerializedName("breeds")
    val breeds: List<Any>? = null,
    @SerializedName("height")
    val height: Int? = null, // 1280
    @SerializedName("id")
    val id: String? = null, // 15fMFiZU5
    @SerializedName("url")
    val url: String? = null, // https://cdn2.thecatapi.com/images/15fMFiZU5.jpg
    @SerializedName("width")
    val width: Int? = null // 720
)
