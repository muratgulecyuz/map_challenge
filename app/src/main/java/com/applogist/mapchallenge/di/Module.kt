package com.applogist.mapchallenge.di

import org.koin.dsl.module

val appModule = module {
    single { NetworkModule().service() }
}
val viewModelModule = module {}
