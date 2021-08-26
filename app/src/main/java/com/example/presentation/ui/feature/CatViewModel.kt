package com.example.presentation.ui.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel
import com.example.domain.GetCatUseCase
import com.example.domain.entities.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    @JvmField @Inject var useCase: GetCatUseCase,
) : BaseViewModel(useCase) {

    private val _content: MutableLiveData<ViewState<*>> = MutableLiveData(ViewState(LCE.Loading))
    val content: LiveData<ViewState<*>> = _content

    fun getCats() = useCase.execute(
        {
            val value = content.value?.state
            _content.postValue(
                when (value) {
                    null,
                    LCE.Loading,
                    is LCE.Error -> ViewState(LCE.Content(it.second))
                    is LCE.Content -> ViewState(value.copy(cats = it.second))
                }
            )
        },
        {
            _content.postValue(
                ViewState(LCE.Error(it))
            )
        },
        params = GetCatUseCase.Params()
    )

    data class ViewState<T : LCE>(val state: T)

    sealed class LCE {
        object Loading : LCE()
        data class Content(val cats: List<CatItem>? = null) : LCE()
        class Error(val error: Throwable? = null) : LCE()
    }
}

