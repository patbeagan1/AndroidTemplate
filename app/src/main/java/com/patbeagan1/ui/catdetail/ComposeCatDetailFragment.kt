package com.patbeagan1.ui.catdetail

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.google.android.material.snackbar.Snackbar
import com.patbeagan1.ui.ComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeCatDetailFragment : ComposeFragment() {

    val viewModel: CatDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            this.view?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG).show() }
        }
    }

    @Composable
    override fun MainContent() {

        val state by viewModel.viewState.observeAsState()

        Column(
            modifier = Modifier
                .background(Color.Green)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello world. ${state?.catName}")
            CatImage(state)
        }
    }

    @Composable
    private fun CatImage(state: CatDetailViewModel.ViewState?) {
        Image(
            painter = rememberImagePainter(state?.catPhoto.toString()),
            contentDescription = "Photo of a cat",
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Preview
    @Composable
    private fun HelloWorld() {
        Text(
            (0..10).fold("") { acc, _ -> acc + "Hello world" },
            Modifier
                .background(Color.Magenta)
                .padding(14.dp),
            Color(0.9F, 0.9F, 0.9F),
        )
    }
}
