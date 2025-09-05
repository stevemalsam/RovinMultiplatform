package com.example.core.rovinmultiplatform.network.di

import com.example.core.rovinmultiplatform.network.ktor.MarsRoverApi
import org.koin.dsl.module

val networkModule = module {
    single<MarsRoverApi> { MarsRoverApi() }
}