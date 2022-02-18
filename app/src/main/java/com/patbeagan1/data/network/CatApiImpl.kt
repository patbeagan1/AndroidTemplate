package com.patbeagan1.data.network

import com.patbeagan1.data.network.model.CatApiModel
import com.patbeagan1.data.source.CatRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatApiImpl : CatRemoteDataSource.CatApi {

    @GET("v1/images/search/")
    override suspend fun getCats(
        @Header("pagination-count") paginationCount: Int,
        @Query("size") size: String,
        @Query("limit") limit: Int,
    ): CatApiModel

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/"

        fun create(apiKey: String): CatApiImpl = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient(apiKey))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApiImpl::class.java)

        private fun getClient(apiKey: String) = OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                chain
                    .request()
                    .newBuilder()
                    .addHeader("x-api-key", apiKey)
                    .build()
                    .let { chain.proceed(it) }
            }
            .build()
    }
}
