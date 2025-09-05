package com.example.core.rovinmultiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
data class JsonResponse(val photos: List<NetworkPhoto>)