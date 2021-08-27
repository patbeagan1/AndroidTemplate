package com.example.data

import com.example.data.network.catapi.CatAPI
import com.example.data.network.catapi.response.CatItem
import com.example.domain.GetCatUseCase
import com.example.domain.entities.CatItemEntity
import com.example.presentation.catdetail.CatDetailViewModel
import io.reactivex.Single
import java.net.URL
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catAPI: CatAPI,
) : GetCatUseCase.Repository, CatDetailViewModel.Repository {

    override fun getCats(): Single<List<CatItemEntity>> = catAPI
        .getCats()
        .map { response -> response.map { it.toCatItemEntity() } }

    override fun getCat(): Single<CatDetailViewModel.CatEntity> = catAPI
        .getCats(paginationCount = 1, limit = 1)
        .map { response -> response.first().toCatDetailEntity() }
}

private fun CatItem.toCatItemEntity(): CatItemEntity = CatItemEntity(
    url ?: "Unknown",
    id ?: "Unknown",
    url?.let { URL(url) } ?: URL(INVALID_IMAGE)
)

private fun CatItem.toCatDetailEntity(): CatDetailViewModel.CatEntity =
    CatDetailViewModel.CatEntity(
        url ?: "Unknown",
        url?.let { URL(url) } ?: URL(INVALID_IMAGE)
    )


const val INVALID_IMAGE =
    "https://bitsofco.de/content/images/2018/12/broken-1.png"