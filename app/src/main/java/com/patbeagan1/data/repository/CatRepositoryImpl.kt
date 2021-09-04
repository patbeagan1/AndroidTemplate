package com.patbeagan1.data.repository

import com.patbeagan1.data.network.catapi.CatService
import com.patbeagan1.domain.GetCatUseCase
import com.patbeagan1.domain.GetSingleCatUseCase
import com.patbeagan1.domain.entities.EntityCatItem
import java.net.URL
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catService: CatService
) : GetCatUseCase.Repository, GetSingleCatUseCase.Repository {

    override suspend fun getCats(): List<EntityCatItem> = catService
        .getCats()
        .map { catItem ->
            EntityCatItem(
                catItem.url ?: "Unknown",
                catItem.id ?: "Unknown",
                catItem.url?.let { URL(it) } ?: URL(INVALID_IMAGE)
            )
        }

    override suspend fun getSingleCat(): EntityCatItem = catService
        .getCats(paginationCount = 1, limit = 1)
        .first()
        .let { catItem ->
            EntityCatItem(
                catItem.url ?: "Unknown",
                catItem.url ?: INVALID_IMAGE,
                catItem.url?.let { URL(it) } ?: URL(INVALID_IMAGE)
            )
        }
}

const val INVALID_IMAGE =
    "https://bitsofco.de/content/images/2018/12/broken-1.png"
