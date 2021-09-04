package com.patbeagan1.data.network.catapi

import com.patbeagan1.data.network.catapi.response.CatResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatService {

    @GET("v1/images/search/")
    suspend fun getCats(
        @Header("pagination-count") paginationCount: Int = 2,
        @Query("size") size: String = "thumb",
        @Query("limit") limit: Int = 5,
    ): CatResponse

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/"

        fun create(apiKey: String): CatService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient(apiKey))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatService::class.java)

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
