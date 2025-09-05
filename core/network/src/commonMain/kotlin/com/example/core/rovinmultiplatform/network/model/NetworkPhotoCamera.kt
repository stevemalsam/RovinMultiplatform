package com.example.core.rovinmultiplatform.network.model

import com.example.rovinmultiplatform.core.model.PhotoCamera
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPhotoCamera(
    val id: Int,
    val name: String,
    @SerialName("rover_id") val roverId: Int,
    @SerialName("full_name") val fullName: String
)

fun NetworkPhotoCamera.asPhotoCamera(): PhotoCamera = PhotoCamera(
    id = id,
    name = name,
    roverId = roverId,
    fullName = fullName
)