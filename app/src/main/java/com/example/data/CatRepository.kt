package com.example.data

import androidx.core.net.toUri
import com.example.data.network.catapi.CatAPI
import com.example.data.network.catapi.response.CatItem
import com.example.domain.GetCatUseCase
import com.example.domain.entities.CatItemEntity
import io.reactivex.Single
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catAPI: CatAPI,
) : GetCatUseCase.Repository {

    override fun getCats(): Single<List<CatItemEntity>> = catAPI
        .getCats()
        .map { response -> response.map { it.toDomainItem() } }
}

private fun CatItem.toDomainItem(): CatItemEntity = CatItemEntity(
    url ?: "Unknown",
    id ?: "Unknown",
    url?.toUri()
)
