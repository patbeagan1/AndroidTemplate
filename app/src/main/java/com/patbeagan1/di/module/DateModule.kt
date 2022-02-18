package com.patbeagan1.di.module

import com.patbeagan1.di.qualifier.DateNow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.util.*

@Module
@InstallIn(ViewModelComponent::class)
class DateModule {
    @DateNow
    @Provides
    fun provideDateNow(): Date = Date()
}