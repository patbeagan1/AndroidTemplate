package com.example.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel(vararg useCases: UseCase) : ViewModel() {
    private val useCaseList = mutableListOf<UseCase>()

    init {
        useCaseList.addAll(useCases)
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach { it.dispose() }
    }
}