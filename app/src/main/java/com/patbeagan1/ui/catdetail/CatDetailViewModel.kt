package com.patbeagan1.ui.catdetail

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patbeagan1.data.repository.INVALID_IMAGE
import com.patbeagan1.domain.GetSingleCatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    var getSingleCatUseCase: GetSingleCatUseCase
) : ViewModel() {

    private var _viewState: MutableLiveData<ViewState> = MutableLiveData<ViewState>(ViewState())
    val viewState: LiveData<ViewState> = _viewState

    private var _snackbar: MutableLiveData<String> = MutableLiveData<String>()
    val snackbar: LiveData<String> = _snackbar

    init {
        viewModelScope.launch {
            try {
                val result = getSingleCatUseCase.getSingleCat()
                _viewState.postValue(
                    viewState.value?.copy(
                        isLoading = false,
                        catName = result.headline,
                        catPhoto = result.imageUri.toString().toUri()
                    )
                )
            } catch (e: Exception) {
                _snackbar.postValue("Exception thrown!\n$e")
            }
        }
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val catPhoto: Uri = INVALID_IMAGE.toUri(),
        val catName: String = "Unknown"
    )
}


