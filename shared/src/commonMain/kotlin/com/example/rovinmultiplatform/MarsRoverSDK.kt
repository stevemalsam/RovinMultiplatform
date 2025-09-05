package com.example.rovinmultiplatform

import com.example.rovinmultiplatform.core.model.JsonResponse
import com.example.rovinmultiplatform.network.MarsRoverApi

class MarsRoverSDK(val api: MarsRoverApi) {

    suspend fun getPhotos(sol: Int, page: Int): JsonResponse {
        return api.getPhotos(sol, page)
    }
}