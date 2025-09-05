package com.example.rovinmultiplatform.core.model

import kotlinx.datetime.LocalDate

data class PhotoRoverData(
    val id: Int,
    val name: String,
    val landingDate: LocalDate,
    val launchDate: LocalDate,
    val status: String
)
