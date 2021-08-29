package com.patbeagan1.ui.catlist

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patbeagan1.domain.GetCatUseCase
import com.patbeagan1.domain.GetDateUseCase
import com.patbeagan1.ui.catlist.adapter.CatHolder
import com.patbeagan1.ui.catlist.adapter.DateHolder
import com.patbeagan1.ui.catlist.adapter.EligibleForRecyclerView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    var catListDataUseCase: GetCatUseCase,
    var dateUseCase: GetDateUseCase
) : ViewModel() {

    private var _spinner: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> = _spinner

    private var _recyclerItems: MutableLiveData<List<EligibleForRecyclerView>> =
        MutableLiveData<List<EligibleForRecyclerView>>()
    val recyclerItems: LiveData<List<EligibleForRecyclerView>> = _recyclerItems

    private var _snackBar: MutableLiveData<String> = MutableLiveData<String>()
    val snackBar: LiveData<String> = _snackBar

    fun getCats() = viewModelScope.launch {
        _spinner.postValue(true)
        try {
            val mapEventToState = catListDataUseCase.getCats()
            val call = dateUseCase.call()
            var counter = 0
            val map = mapEventToState.cats.map { item ->
                CatHolder.ViewModel(
                    item.title,
                    item.headline,
                    item.imageUri.toString().toUri(),
                    counter++
                )
            }
            _recyclerItems.postValue(
                listOf(
                    DateHolder.ViewModel(
                        call.dateItem.date,
                        counter++
                    )
                ) + map
            )
            _spinner.postValue(false)
        } catch (e: Exception) {
            presentError("Something went wrong!\n$e")
        }

//        catListDataUseCase.execute(
//            { catResult ->
//                _recyclerItems.postValue(catResult.let { (dateResults, catResults) ->
//                    var counter = 0
//                    val dateDataModels = listOf(
//                        DateHolder.DataModel(
//                            dateResults.dateItem.date,
//                            counter++
//                        )
//                    )
//                    val catDataModels = catResults.cats.map { item ->
//                        CatHolder.DataModel(
//                            item.title,
//                            item.headline,
//                            item.imageUri.toString().toUri(),
//                            counter++
//                        )
//                    }
//                    dateDataModels + catDataModels
//                })
//            },
//            {
//                presentError("Something went wrong!\n$it")
//            }, params = GetCatListDataUseCase.Param()
//        )
    }

    private fun presentError(errorString: String) {
        _spinner.postValue(false)
        _snackBar.postValue(errorString)
    }
}
