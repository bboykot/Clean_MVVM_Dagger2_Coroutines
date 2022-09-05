package com.bboykot.weather.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.app.App
import com.bboykot.weather.app.di.component.SearchComponent
import com.bboykot.weather.databinding.ActivityMainBinding
import com.bboykot.weather.presentation.search.SearchFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    lateinit var searchSubcomponent: SearchComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        searchSubcomponent = (application as App).appComponent.getSearchComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding.tvTest.text = "123"
        if (savedInstanceState == null)
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_container, SearchFragment(), null).commit()
    }
}