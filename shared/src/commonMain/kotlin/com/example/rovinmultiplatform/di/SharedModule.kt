package com.example.rovinmultiplatform.di

import com.example.core.rovinmultiplatform.network.di.networkModule
import com.example.rovinmultiplatform.MarsRoverSDK
import org.koin.dsl.module

val sharedModule = module {
    includes(networkModule)
    single<MarsRoverSDK> {
        MarsRoverSDK(
            api = get()
        )
    }
}