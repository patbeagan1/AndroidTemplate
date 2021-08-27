package com.example.di

import com.example.R
import com.example.data.CatRepository
import com.example.data.network.catapi.CatAPI
import com.example.di.qualifiers.DateFormatDayMonth
import com.example.di.qualifiers.DateNow
import com.example.di.qualifiers.ThreadIO
import com.example.di.qualifiers.ThreadMain
import com.example.domain.GetCatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {
    @DateFormatDayMonth
    @Provides
    fun providesSimpleDateFormat(): SimpleDateFormat = SimpleDateFormat("dd MMM")
}

@Module
@InstallIn(ViewModelComponent::class)
class Module {

    @Provides
    fun provideCatRepository(CatRepository: CatRepository): GetCatUseCase.Repository =
        CatRepository

    @Provides
    fun provideCatApi(resourcesHelper: ResourcesHelper): CatAPI =
        CatAPI.create(resourcesHelper.getString(R.string.cat_api_key))

    @ThreadIO
    @Provides
    fun provideSchedulerIo(): Scheduler = Schedulers.io()

    @ThreadMain
    @Provides
    fun provideSchedulerMain(): Scheduler = AndroidSchedulers.mainThread()

    @DateNow
    @Provides
    fun provideDateNow(): Date = Date()
}