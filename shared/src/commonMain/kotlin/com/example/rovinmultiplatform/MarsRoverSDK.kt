package com.example.rovinmultiplatform

import com.example.core.rovinmultiplatform.network.ktor.MarsRoverApi
import com.example.core.rovinmultiplatform.network.model.asPhoto
import com.example.rovinmultiplatform.core.model.Photo

class MarsRoverSDK(val api: MarsRoverApi) {

    suspend fun getPhotos(sol: Int, page: Int): List<Photo> {
        val networkResponse = api.getPhotos(sol, page)
        return networkResponse.map { it.asPhoto() }
    }
}