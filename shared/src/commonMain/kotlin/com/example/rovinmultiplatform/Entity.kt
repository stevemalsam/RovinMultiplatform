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
    val rover: Rover
)

@Serializable
data class PhotoCamera(
    val id: Int,
    val name: String,
    @SerialName("rover_id") val roverId: Int,
    @SerialName("full_name") val fullName: String
)

@Serializable
data class Rover(
    val id: Int,
    val name: String,
    @SerialName("landing_date") val landingDate: LocalDate,
    @SerialName("launch_date") val launchDate: LocalDate,
    val status: String = "active",
    @SerialName("max_sol") val maxSol: Int,
    @SerialName("max_date") val maxDate: LocalDate,
    @SerialName("total_photos") val totalPhotos: Int = 0,
    val cameras: List<RoverCamera>
)

@Serializable
data class RoverCamera(val name: String, @SerialName("full_name") val fullName: String)