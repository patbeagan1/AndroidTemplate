package com.example.presentation.ui.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.ViewBindingFragment
import com.example.databinding.FragmentCatBinding
import com.example.domain.entities.CatItem
import com.example.presentation.ui.feature.entities.CatViewItem
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatFragment : ViewBindingFragment<FragmentCatBinding>() {
    private val viewModel by viewModels<CatViewModel>()
    private val catRecyclerviewAdapter = CatRecyclerviewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding { bindRecyclerview() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.content.observe(this, { viewState -> render(viewState) })
        viewModel.getCats()
    }

    private fun render(viewState: CatViewModel.ViewState<*>) = withBinding {
        when (viewState.state) {
            CatViewModel.LCE.Loading -> showProgressBar()
            is CatViewModel.LCE.Content -> {
                viewState.state.cats?.let { it1 -> appendToRecyclerview(it1) }
                hideProgressBar()
            }
            is CatViewModel.LCE.Error -> {
                hideProgressBar()
                showSnackbar("Could not get your Cats!\n${viewState.state.error}")
            }
        }
    }

    private fun appendToRecyclerview(list: List<CatItem>) {
        val data = catRecyclerviewAdapter.data
        val lastIndex = data.lastIndex
        data.addAll(list.map {
            CatViewItem(
                it.title,
                it.headline,
                it.imageUri
            )
        })
        catRecyclerviewAdapter.notifyItemRangeInserted(lastIndex, list.size)
    }

    private fun FragmentCatBinding.bindRecyclerview() {
        recyclerview.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        recyclerview.adapter = catRecyclerviewAdapter
    }

    private fun FragmentCatBinding.showSnackbar(text: String) = Snackbar.make(
        CatContainer,
        text,
        Snackbar.LENGTH_LONG
    ).show()

    private fun FragmentCatBinding.hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun FragmentCatBinding.showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun generateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCatBinding = FragmentCatBinding.inflate(inflater)
}

