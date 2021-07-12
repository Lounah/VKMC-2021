package com.lounah.vkmc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lounah.vkmc.R
import com.lounah.vkmc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.root)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() = with(binding) {
        taxi.setOnClickListener {}
        newsfeed.setOnClickListener {}
        voice.setOnClickListener {}
    }
}
