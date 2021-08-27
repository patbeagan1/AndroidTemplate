package com.example.presentation.ui.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.domain.GetCatUseCase
import com.example.domain.GetDateUseCase
import com.example.presentation.ui.feature.adapter.CatHolder
import com.example.presentation.ui.feature.adapter.DateHolder
import com.example.presentation.ui.feature.adapter.EligibleForRecyclerView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    @JvmField @Inject var catProvider: GetCatUseCase,
    @JvmField @Inject var dateProvider: GetDateUseCase,
) : BaseViewModel(catProvider, dateProvider) {

    private var _spinner: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> = _spinner

    private var _recyclerItems: MutableLiveData<List<EligibleForRecyclerView>> =
        MutableLiveData<List<EligibleForRecyclerView>>()
    val recyclerItems: LiveData<List<EligibleForRecyclerView>> = _recyclerItems

    private var _snackBar: MutableLiveData<String> = MutableLiveData<String>()
    val snackBar: LiveData<String> = _snackBar

    fun getCats() {
        _spinner.postValue(true)
        catProvider.execute({ catResult ->
            when (catResult) {
                is GetCatUseCase.Result.Success -> {
                    dateProvider.execute({ dateResult ->
                        _recyclerItems.postValue(generateList(dateResult, catResult))
                        _spinner.postValue(false)
                    }, {
                        presentError("Something went wrong!\n$it")
                    }, params = GetDateUseCase.Param())
                }
            }
        }, {
            presentError("Something went wrong!\n$it")
        }, params = GetCatUseCase.Params())
    }

    private fun generateList(
        dateResult: GetDateUseCase.Results,
        catResult: GetCatUseCase.Result.Success
    ): List<EligibleForRecyclerView> {
        val dateList = listOf(DateHolder.DataModel(dateResult.dateItem.date, -1))
        val catList = catResult.cats.mapIndexed { index, item ->
            CatHolder.DataModel(
                item.title,
                item.headline,
                item.imageUri,
                index
            )
        }
        return dateList + catList
    }

    private fun presentError(errorString: String) {
        _spinner.postValue(false)
        _snackBar.postValue(errorString)
    }
}
