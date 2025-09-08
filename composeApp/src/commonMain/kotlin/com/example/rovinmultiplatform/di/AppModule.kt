package com.example.rovinmultiplatform.di

import org.koin.dsl.module

val appModule = module {
    includes(sharedModule)
}