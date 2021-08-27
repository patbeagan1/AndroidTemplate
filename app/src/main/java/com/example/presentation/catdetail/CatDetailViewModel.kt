package com.example.presentation.catdetail

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import com.example.base.BaseViewModel
import com.example.data.INVALID_IMAGE
import com.example.di.qualifiers.ThreadIO
import com.example.di.qualifiers.ThreadMain
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    repository: Repository,
    @ThreadMain threadMain: Scheduler,
    @ThreadIO threadIO: Scheduler
) : BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _viewState: MutableLiveData<ViewState> = MutableLiveData<ViewState>(ViewState())
    val viewState: LiveData<ViewState> = _viewState

    private var _snackbar: MutableLiveData<String> = MutableLiveData<String>()
    val snackbar: LiveData<String> = _snackbar

    init {
        repository
            .getCat()
            .subscribeOn(threadIO)
            .observeOn(threadMain)
            .subscribe({
                _viewState.postValue(
                    viewState.value?.copy(
                        isLoading = false,
                        catName = it.name,
                        catPhoto = it.image.toString().toUri()
                    )
                )
            }, {
                _snackbar.postValue("Exception thrown!\n$it")
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    interface Repository {
        fun getCat(): Single<CatEntity>
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val catPhoto: Uri = INVALID_IMAGE.toUri(),
        val catName: String = "Unknown"
    )

    data class CatEntity(val name: String, val image: URL)
}


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("srcCoil")
    fun srcCoil(image: ImageView, uri: Uri?) {
        image.load(uri)
    }

    @JvmStatic
    @BindingAdapter("visibleInvisible")
    fun visibleInvisible(view: View, isVisible: Boolean?) {
        view.visibility = if (isVisible == true) View.VISIBLE else View.INVISIBLE
    }
}
