package com.applogist.mapchallenge.di

import com.applogist.mapchallenge.ui.map.MapViewModel
import com.applogist.mapchallenge.ui.trips.TripsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NetworkModule().service() }
}
val viewModelModule = module {
    viewModel { MapViewModel(get()) }
    viewModel { TripsViewModel(get()) }
}
