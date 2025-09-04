package com.example.rovinmultiplatform

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonResponse(val photos: List<Photo>)

@Serializable
data class Photo(
    val id: Int,
    val sol: Int,
    val camera: PhotoCamera,
    @SerialName("img_src") val imgSrc: String,
    @SerialName("earth_date") val earthDate: LocalDate,
    val rover: PhotoRoverData
)

@Serializable
data class PhotoCamera(
    val id: Int,
    val name: String,
    @SerialName("rover_id") val roverId: Int,
    @SerialName("full_name") val fullName: String
)

@Serializable
data class PhotoRoverData(
    val id: Int,
    val name: String,
    @SerialName("landing_date") val landingDate: LocalDate,
    @SerialName("launch_date") val launchDate: LocalDate,
    val status: String = "active"
)

@Serializable
data class RoverCamera(val name: String, @SerialName("full_name") val fullName: String)