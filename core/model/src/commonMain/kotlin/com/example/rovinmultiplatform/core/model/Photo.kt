package com.example.rovinmultiplatform.core.model

import kotlinx.datetime.LocalDate

data class Photo(
    val id: Int,
    val sol: Int,
    val camera: PhotoCamera,
    val imageURL: String,
    val earthDate: LocalDate,
    val rover: PhotoRoverData
    )
