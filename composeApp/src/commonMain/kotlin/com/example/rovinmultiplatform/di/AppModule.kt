package com.example.rovinmultiplatform.di

import com.example.rovinmultiplatform.MarsRoverSDK
import org.koin.dsl.module

import com.example.core.rovinmultiplatform.network.di.networkModule

val appModule = module {
    includes(networkModule)
    single<MarsRoverSDK> {
        MarsRoverSDK(
            api = get()
        )
    }
}