package com.example.ui

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("srcCoil")
    fun srcCoil(image: ImageView, uri: Uri?) {
        image.load(uri)
    }

    @JvmStatic
    @BindingAdapter("bindInvisibleUnless")
    fun invisibleUnless(view: View, isVisible: Boolean?) {
        view.visibility = if (isVisible == true) View.VISIBLE else View.INVISIBLE
    }
}