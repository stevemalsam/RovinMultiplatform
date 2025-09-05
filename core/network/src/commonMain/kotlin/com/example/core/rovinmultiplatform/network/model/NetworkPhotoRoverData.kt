package com.example.core.rovinmultiplatform.network.model

import com.example.rovinmultiplatform.core.model.PhotoRoverData
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPhotoRoverData(
    val id: Int,
    val name: String,
    @SerialName("landing_date") val landingDate: LocalDate,
    @SerialName("launch_date") val launchDate: LocalDate,
    val status: String = "active"
)

fun NetworkPhotoRoverData.asPhotoRoverData(): PhotoRoverData = PhotoRoverData(
    id = id,
    name = name,
    landingDate = landingDate,
    launchDate = launchDate,
    status = status
)