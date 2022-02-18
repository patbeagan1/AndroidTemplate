package com.patbeagan1.data.repository

import com.patbeagan1.data.source.CatRemoteDataSource
import com.patbeagan1.data.util.MemoryCache
import com.patbeagan1.data.util.invoke
import com.patbeagan1.domain.GetCatUseCase
import com.patbeagan1.domain.GetSingleCatUseCase
import com.patbeagan1.domain.entities.EntityCatItem
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val dataSource: CatRemoteDataSource,
) : GetCatUseCase.Repository,
    GetSingleCatUseCase.Repository {

    private val catCache = MemoryCache<List<EntityCatItem>>(emptyList()) { it.isEmpty() }

    override suspend fun getCats(refresh: Boolean): List<EntityCatItem> =
        catCache.invoke(refresh) { dataSource.getCats() }

    override suspend fun getSingleCat(): EntityCatItem =
        dataSource.getSingleCat()
}
