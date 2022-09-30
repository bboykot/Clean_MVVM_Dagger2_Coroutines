package com.bboykot.weather.app.di.forecast

import android.os.Bundle
import com.bboykot.weather.presentation.day.DayForecastFragment
import com.bboykot.weather.presentation.week.WeekForecastFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ForecastModule::class])
interface ForecastComponent {


    @Subcomponent.Builder
    interface ForecastCompBuilder{
        @BindsInstance
        fun extras(extras: Bundle?): ForecastCompBuilder
        fun buildForecastComp(): ForecastComponent
    }


    fun injectDayForecastFragment(fragment: DayForecastFragment)
    fun injectWeekForecastViewModel(fragment: WeekForecastFragment)
}