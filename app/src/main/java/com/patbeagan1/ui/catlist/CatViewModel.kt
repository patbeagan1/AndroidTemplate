package com.patbeagan1.ui.catlist

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.patbeagan1.R
import com.patbeagan1.domain.GetCatUseCase
import com.patbeagan1.domain.GetDateUseCase
import com.patbeagan1.ui.catlist.adapter.CatHolder
import com.patbeagan1.ui.catlist.adapter.DateHolder
import com.patbeagan1.ui.catlist.adapter.EligibleForRecyclerView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class CatViewModel @Inject constructor(
    var catListDataUseCase: GetCatUseCase,
    private var dateUseCase: GetDateUseCase
) : ViewModel() {

    private var _spinner: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> = _spinner

    private var _recyclerItems: MutableLiveData<List<EligibleForRecyclerView>> =
        MutableLiveData<List<EligibleForRecyclerView>>()
    val recyclerItems: LiveData<List<EligibleForRecyclerView>> = _recyclerItems

    private var _snackBar: MutableLiveData<String> = MutableLiveData<String>()
    val snackBar: LiveData<String> = _snackBar

    @FlowPreview
    fun getCats() = viewModelScope.launch(Dispatchers.IO) {
        _spinner.postValue(true)
        try {
            var counter = 0

            val catList = catListDataUseCase(GetCatUseCase.Params()).map { result ->
                result.cats.map { catItem ->
                    CatHolder.ViewModel(
                        catItem.title + counter,
                        catItem.headline,
                        catItem.imageUri.toString().toUri(),
                        counter++
                    ) {
                        it.findNavController()
                            .navigate(R.id.action_catFragment_to_composeCatDetailFragment)
                    }
                }
            }

            val dateList = dateUseCase(GetDateUseCase.Params()).map { results ->
                listOf(
                    DateHolder.ViewModel(
                        results.dateItem.date,
                        counter++
                    ) {
                        it.findNavController()
                            .navigate(R.id.action_catFragment_to_catDetailFragment)
                    }
                )
            }

            dateList.combine(catList) { a, b -> a + b }.collect {
                _recyclerItems.postValue(it)
                _spinner.postValue(false)
            }
        } catch (e: Exception) {
            _spinner.postValue(false)
            presentError("Something went wrong!\n$e")
        }
    }

    private fun presentError(errorString: String) {
        _spinner.postValue(false)
        _snackBar.postValue(errorString)
    }
}
