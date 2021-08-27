package com.example.presentation.catdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.databinding.FragmentCatDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatDetailFragment : Fragment() {

    private val viewModel: CatDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCatDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root

        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.viewModel = it
        }

        viewModel.snackbar.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }

        return binding.root
    }
}