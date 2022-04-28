package com.mapchallenge.di

import com.mapchallenge.ui.map.MapViewModel
import com.mapchallenge.ui.trips.TripsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NetworkModule().service() }
}
val viewModelModule = module {
    viewModel { MapViewModel(get()) }
    viewModel { TripsViewModel(get()) }
}
