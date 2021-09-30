package com.patbeagan1.ui.catlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.patbeagan1.R
import com.patbeagan1.databinding.FragmentCatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatFragment : Fragment() {
    private val catViewModel by viewModels<CatViewModel>()
    private val catRecyclerviewAdapter = CatRecyclerviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCatBinding>(
            inflater,
            R.layout.fragment_cat,
            container,
            false
        ) ?: return null

        binding.recyclerview.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerview.adapter = catRecyclerviewAdapter
        binding.viewModel = catViewModel

        catViewModel.recyclerItems.observe(viewLifecycleOwner) {
//            catRecyclerviewAdapter.submitList(it)
            catRecyclerviewAdapter.data.clear()
            catRecyclerviewAdapter.data.addAll(it)
            catRecyclerviewAdapter.notifyDataSetChanged()
        }

        catViewModel.snackBar.observe(viewLifecycleOwner) {
            Snackbar.make(binding.container, it, Snackbar.LENGTH_LONG).show()
        }

        catViewModel.spinner.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it == true) View.VISIBLE else View.INVISIBLE
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        catViewModel.getCats()
    }
}
