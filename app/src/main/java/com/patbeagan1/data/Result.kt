package com.patbeagan1.data

sealed class Result<out R> {
    data class Success<out T>(val data: T) : com.patbeagan1.data.Result<T>()
    data class Error(val exception: Exception) : com.patbeagan1.data.Result<Nothing>()
}