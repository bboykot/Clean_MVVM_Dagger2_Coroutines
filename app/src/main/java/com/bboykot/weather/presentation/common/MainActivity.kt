package com.bboykot.weather.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bboykot.weather.R
import com.bboykot.weather.app.App
import com.bboykot.weather.app.di.component.HomeComponent
import com.bboykot.weather.app.di.component.SearchComponent
import com.bboykot.weather.databinding.ActivityMainBinding
import com.bboykot.weather.presentation.home.HomeFragment
import com.bboykot.weather.presentation.search.SearchFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    lateinit var searchSubcomponent: SearchComponent
    lateinit var homeComponent: HomeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        searchSubcomponent = (application as App).appComponent.getSearchComponent()
        homeComponent = (application as App).appComponent.getHomeComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> HomeFragment() to HOME_TAG
                R.id.navigation_search -> SearchFragment() to SEARCH_TAG
                else -> null
            }?.also {
                val fragment = supportFragmentManager.findFragmentByTag(it.second)
                if (fragment != null) {
                    supportFragmentManager.beginTransaction().addToBackStack(it.second)
                        .replace(R.id.main_container,fragment, it.second)
                        .setReorderingAllowed(true).commit()
                } else
                    supportFragmentManager.beginTransaction().addToBackStack(it.second)
                        .replace(R.id.main_container, it.first, it.second)
                        .setReorderingAllowed(true).commit()
            }
            true
        }
    }

    companion object{
        private const val HOME_TAG = "home_tag"
        private const val SEARCH_TAG = "search_tag"
    }
}