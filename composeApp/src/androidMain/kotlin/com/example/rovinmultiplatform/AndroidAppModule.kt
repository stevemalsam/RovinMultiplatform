package com.example.rovinmultiplatform

import com.example.rovinmultiplatform.di.appModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidAppModule = module {
    includes(appModule)
    viewModel {
        MarsPhotoViewModel(
            sdk = get()
        )
    }
}