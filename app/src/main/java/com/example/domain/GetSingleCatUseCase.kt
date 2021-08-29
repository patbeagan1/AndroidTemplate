package com.example.domain

import com.example.domain.entities.EntityCatItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSingleCatUseCase @Inject constructor(
    val repository: Repository
) {

    suspend fun getSingleCat() = withContext(Dispatchers.IO) {
        repository.getSingleCat()
    }

    interface Repository {
        suspend fun getSingleCat(): EntityCatItem
    }
}
