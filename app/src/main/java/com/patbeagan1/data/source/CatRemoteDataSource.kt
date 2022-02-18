package com.patbeagan1.data.source

import com.patbeagan1.data.network.model.CatApiModel
import com.patbeagan1.data.network.model.CatItem
import com.patbeagan1.di.qualifier.InvalidImage
import com.patbeagan1.di.qualifier.NetworkDispatcher
import com.patbeagan1.domain.entities.EntityCatItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

class CatRemoteDataSource @Inject constructor(
    private val catApi: CatApi,
    @NetworkDispatcher private val dispatcher: CoroutineDispatcher,
    @InvalidImage private val invalidImageString: String
) {

    suspend fun getCats(): List<EntityCatItem> = withContext(dispatcher) {
        catApi
            .getCats()
            .map { it.toEntity() }
    }

    suspend fun getSingleCat(): EntityCatItem = withContext(dispatcher) {
        catApi
            .getCats(paginationCount = 1, limit = 1)
            .first()
            .toEntity()
    }

    /**
     * A networked api for getting cat data
     */
    interface CatApi {
        /**
         * @return a list of cats
         */
        suspend fun getCats(
            paginationCount: Int = 2,
            size: String = "thumb",
            limit: Int = 5,
        ): CatApiModel
    }

    private fun CatItem.toEntity() = EntityCatItem(
        url ?: "Unknown",
        id ?: "Unknown",
        url?.let { URL(it) } ?: URL(invalidImageString)
    )
}
