package com.patbeagan1.util

import android.util.Log

internal fun reportThread(id: Int) {
    Log.d("TRD-RPT", "$id ${Thread.currentThread().name}")
}