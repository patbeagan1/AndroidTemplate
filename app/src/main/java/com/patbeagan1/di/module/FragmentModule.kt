package com.patbeagan1.di.module

import com.patbeagan1.di.qualifier.DateFormatDayMonth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import java.text.SimpleDateFormat

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {
    @DateFormatDayMonth
    @Provides
    fun providesSimpleDateFormat(): SimpleDateFormat = SimpleDateFormat("dd MMM")
}
