package com.example.data.network.catapi

import com.example.data.network.catapi.response.CatResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatAPI {

    @GET("v1/images/search/")
    fun getCats(
        @Header("pagination-count") paginationCount: Int = 2,
        @Query("size") size: String = "thumb",
        @Query("limit") limit: Int = 5,
    ): Single<CatResponse>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/"

        fun create(apiKey: String): CatAPI = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient(apiKey))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatAPI::class.java)

        private fun getClient(apiKey: String) = OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
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