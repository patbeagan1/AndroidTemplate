package com.patbeagan1.ui.catdetail

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patbeagan1.R
import com.patbeagan1.di.qualifier.InvalidImage
import com.patbeagan1.domain.GetSingleCatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    private var getSingleCatUseCase: GetSingleCatUseCase,
    @InvalidImage private val invalidImage: Uri
) : ViewModel() {
    private var _viewState: MutableLiveData<ViewState> =
        MutableLiveData<ViewState>(
            ViewState(
                isLoading = true,
                catPhoto = invalidImage,
                catName = "Unknown"
            )
        )
    val viewState: LiveData<ViewState> = _viewState

    private var _snackbar: MutableLiveData<String> = MutableLiveData<String>()
    val snackbar: LiveData<String> = _snackbar

    init {
        viewModelScope.launch {
            try {
                val result = getSingleCatUseCase(GetSingleCatUseCase.Params()).single().data
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
        val isLoading: Boolean,
        val catPhoto: Uri,
        val catName: String,
    )
}
