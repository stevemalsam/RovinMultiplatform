package com.example.core.rovinmultiplatform.network.model

import com.example.rovinmultiplatform.core.model.Photo
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPhoto(
    val id: Int,
    val sol: Int,
    val camera: NetworkPhotoCamera,
    @SerialName("img_src") val imgSrc: String,
    @SerialName("earth_date") val earthDate: LocalDate,
    val rover: NetworkPhotoRoverData
)

fun NetworkPhoto.asPhoto(): Photo = Photo(
    id = id,
    sol = sol,
    camera = camera.asPhotoCamera(),
    imageURL = imgSrc,
    earthDate = earthDate,
    rover = rover.asPhotoRoverData()
)