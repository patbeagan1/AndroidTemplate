package com.patbeagan1.di.module

import com.patbeagan1.R
import com.patbeagan1.data.network.CatApiImpl
import com.patbeagan1.di.util.ResourcesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {
    @Provides
    fun provideCatApi(resourcesHelper: ResourcesHelper): CatApiImpl =
        CatApiImpl.create(resourcesHelper.getString(R.string.cat_api_key))
}

