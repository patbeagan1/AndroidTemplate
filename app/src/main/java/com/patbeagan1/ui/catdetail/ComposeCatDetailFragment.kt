package com.patbeagan1.ui.catdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
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

    @Preview
    @Composable
    fun myContent() {

        val state by viewModel.viewState.observeAsState()

        Column(
            modifier = Modifier
                .background(Color.Green)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Hello world. ${state?.catName}")
            Image(
                painter = rememberImagePainter(state?.catPhoto.toString()),
                contentDescription = "Photo of a cat",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
