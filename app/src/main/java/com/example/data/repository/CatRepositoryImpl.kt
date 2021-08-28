package com.example.data.repository

import com.example.data.network.catapi.CatService
import com.example.domain.GetCatUseCase
import com.example.domain.GetSingleCatUseCase
import com.example.domain.entities.CatItemEntity
import java.net.URL
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catService: CatService,
) : GetCatUseCase.Repository, GetSingleCatUseCase.Repository {

    override suspend fun getCats(): List<CatItemEntity> = catService
        .getCats().map { catItem ->
            CatItemEntity(
                catItem.url ?: "Unknown",
                catItem.id ?: "Unknown",
                catItem.url?.let { URL(it) } ?: URL(INVALID_IMAGE)
            )
        }

    override suspend fun getSingleCat(): CatItemEntity = catService
       .getCats(paginationCount = 1, limit = 1).first().let { catItem ->
           CatItemEntity(
               catItem.url ?: "Unknown",
               catItem.url ?: INVALID_IMAGE,
               catItem.url?.let { URL(it) } ?: URL(INVALID_IMAGE)
           )
       }
}

const val INVALID_IMAGE =
    "https://bitsofco.de/content/images/2018/12/broken-1.png"