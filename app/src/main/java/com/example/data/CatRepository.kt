package com.example.data

import androidx.core.net.toUri
import com.example.data.network.catapi.CatAPI
import com.example.domain.GetCatUseCase
import com.example.domain.entities.CatItem
import io.reactivex.Single
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catAPI: CatAPI,
) : GetCatUseCase.Contract.Repository {

    override fun getCats(): Single<List<CatItem>> = catAPI
        .getCats()
        .map { response ->
            response.map {
                CatItem(
                    it.url ?: "Unknown",
                    it.id ?: "Unknown",
                    it.url?.toUri()
                )
            }
        }
}