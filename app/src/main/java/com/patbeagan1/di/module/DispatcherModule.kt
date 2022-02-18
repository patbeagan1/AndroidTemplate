package com.patbeagan1.di.module

import com.patbeagan1.di.qualifier.NetworkDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class DispatcherModule {
    @NetworkDispatcher
    @Provides
    fun networkDispatcher(): CoroutineDispatcher =
        Dispatchers.IO
}
