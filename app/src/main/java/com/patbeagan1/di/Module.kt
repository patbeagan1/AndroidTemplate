package com.patbeagan1.di

//import io.reactivex.Scheduler
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.schedulers.Schedulers
import android.net.Uri
import androidx.core.net.toUri
import com.patbeagan1.R
import com.patbeagan1.data.network.catapi.CatService
import com.patbeagan1.data.repository.CatRepositoryImpl
import com.patbeagan1.di.qualifiers.DateFormatDayMonth
import com.patbeagan1.di.qualifiers.DateNow
import com.patbeagan1.di.qualifiers.InvalidImage
import com.patbeagan1.domain.GetCatUseCase
import com.patbeagan1.domain.GetSingleCatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import java.net.URL
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
    fun provideCatRepository(repository: CatRepositoryImpl): GetCatUseCase.Repository =
        repository

    @Provides
    fun provideSingleCatRepository(repository: CatRepositoryImpl): GetSingleCatUseCase.Repository =
        repository

    @Provides
    fun provideCatApi(resourcesHelper: ResourcesHelper): CatService =
        CatService.create(resourcesHelper.getString(R.string.cat_api_key))

//    @ThreadIO
//    @Provides
//    fun provideSchedulerIo(): Scheduler = Schedulers.io()
//
//    @ThreadMain
//    @Provides
//    fun provideSchedulerMain(): Scheduler = AndroidSchedulers.mainThread()

    @DateNow
    @Provides
    fun provideDateNow(): Date = Date()

    @InvalidImage
    @Provides
    fun providesInvalidImageString(): String =
        "https://bitsofco.de/content/images/2018/12/broken-1.png"

    @InvalidImage
    @Provides
    fun providesInvalidImageUrl(@InvalidImage imageUrl: String): URL = URL(imageUrl)

    @InvalidImage
    @Provides
    fun providesInvalidImageUri(@InvalidImage imageUrl: String): Uri =
        URL(imageUrl).toString().toUri()

}