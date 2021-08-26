package com.example.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Helper class that does viewbinding setup and breakdown for a fragment.
 */
abstract class ViewBindingFragment<T : ViewBinding> : Fragment() {
    var binding: T? = null

    inline infix fun withBinding(configure: T.(context: Context) -> Unit) =
        binding?.let { safeBinding ->
            safeBinding.root.context?.let {
                safeBinding.configure(it)
            }
        } ?: Unit

    abstract fun generateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = generateBinding(inflater, container, savedInstanceState).also { binding = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}