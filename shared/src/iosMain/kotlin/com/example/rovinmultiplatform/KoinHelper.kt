package com.example.rovinmultiplatform

import com.example.core.rovinmultiplatform.network.di.networkModule
import com.example.core.rovinmultiplatform.network.ktor.MarsRoverApi
import com.example.rovinmultiplatform.core.model.Photo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinHelper: KoinComponent {
    private val marsRoverSDK: MarsRoverSDK by inject<MarsRoverSDK>()

    suspend fun getPhotos(sol: Int, page: Int): List<Photo> {
        return marsRoverSDK.getPhotos(sol, page)
    }
}

fun initKoin() {
    startKoin {
        modules(module {
            includes(networkModule)
            single<MarsRoverSDK> { MarsRoverSDK(
                api = get<MarsRoverApi>()
            ) }
        })
    }
}