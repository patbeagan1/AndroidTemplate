package com.patbeagan1.di.module

import com.patbeagan1.data.network.CatApiImpl
import com.patbeagan1.data.repository.CatRepository
import com.patbeagan1.data.source.CatRemoteDataSource
import com.patbeagan1.domain.GetCatUseCase
import com.patbeagan1.domain.GetSingleCatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseRepositoryModule {
    @Provides
    fun provideCatRepository(repository: CatRepository): GetCatUseCase.Repository =
        repository

    @Provides
    fun provideSingleCatRepository(repository: CatRepository): GetSingleCatUseCase.Repository =
        repository

    @Provides
    fun provideCatApi(api: CatApiImpl): CatRemoteDataSource.CatApi = api
}