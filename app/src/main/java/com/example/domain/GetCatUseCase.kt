package com.example.domain

import com.example.domain.entities.CatItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCatUseCase @Inject constructor(
    private val repository: Repository,
) {
    data class Result(val cats: List<CatItemEntity>)

    suspend fun getCats(): Result = withContext(Dispatchers.IO) {
        Result(
            repository.getCats()
        )
    }

    interface Repository {
        suspend fun getCats(): List<CatItemEntity>
    }
}

