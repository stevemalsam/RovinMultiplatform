package com.example.rovinmultiplatform

import com.example.rovinmultiplatform.network.MarsRoverApi
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<MarsRoverApi> { MarsRoverApi() }
    single<MarsRoverSDK> {
        MarsRoverSDK(
            api = get()
        )
    }
    viewModel {
        MarsPhotoViewModel(
            sdk = get()
        )
    }
}