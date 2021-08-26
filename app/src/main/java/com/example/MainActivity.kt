package com.example

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.presentation.ui.feature.CatFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, CatFragment())
        }.commit()
    }
}