package com.example.presentation.catlist

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.domain.GetCatListDataUseCase
import com.example.presentation.catlist.adapter.CatHolder
import com.example.presentation.catlist.adapter.DateHolder
import com.example.presentation.catlist.adapter.EligibleForRecyclerView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    @JvmField var catListDataUseCase: GetCatListDataUseCase
) : BaseViewModel(catListDataUseCase) {

    private var _spinner: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> = _spinner

    private var _recyclerItems: MutableLiveData<List<EligibleForRecyclerView>> =
        MutableLiveData<List<EligibleForRecyclerView>>()
    val recyclerItems: LiveData<List<EligibleForRecyclerView>> = _recyclerItems

    private var _snackBar: MutableLiveData<String> = MutableLiveData<String>()
    val snackBar: LiveData<String> = _snackBar

    fun getCats() {
        _spinner.postValue(true)
        catListDataUseCase.execute(
            { catResult ->
                _recyclerItems.postValue(catResult.let { (dateResults, catResults) ->
                    var counter = 0
                    val dateDataModels = listOf(
                        DateHolder.DataModel(
                            dateResults.dateItem.date,
                            counter++
                        )
                    )
                    val catDataModels = catResults.cats.map { item ->
                        CatHolder.DataModel(
                            item.title,
                            item.headline,
                            item.imageUri.toString().toUri(),
                            counter++
                        )
                    }
                    dateDataModels + catDataModels
                })
            },
            {
                presentError("Something went wrong!\n$it")
            }, params = GetCatListDataUseCase.Param()
        )
    }

    private fun presentError(errorString: String) {
        _spinner.postValue(false)
        _snackBar.postValue(errorString)
    }
}
