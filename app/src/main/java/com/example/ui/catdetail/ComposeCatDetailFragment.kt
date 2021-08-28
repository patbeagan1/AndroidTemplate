package com.example.ui.catdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeCatDetailFragment : Fragment() {

    private val viewModel: CatDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext()).apply {
            setContent {
                myContent()
            }
        }

        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            this.view?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG).show() }
        }

        return view
    }

    @Composable
    private fun myContent() {

        val state by viewModel.viewState.observeAsState()

        Text(text = "Hello world. ${state?.catName}")
    }
}
