package com.bboykot.weather.app.di.component.day

import android.os.Bundle
import com.bboykot.weather.presentation.day.DayForecastFragment
import com.bboykot.weather.presentation.week.WeekForecastFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [DayForecastModule::class])
interface DayForecastComponent {


    @Subcomponent.Builder
    interface ForecastCompBuilder{
        @BindsInstance
        fun extras(extras: Bundle?): ForecastCompBuilder
        fun buildForecastComp(): DayForecastComponent
    }


    fun injectDayForecastFragment(fragment: DayForecastFragment)
    fun injectWeekForecastViewModel(fragment: WeekForecastFragment)
}