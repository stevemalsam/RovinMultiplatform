package com.example.rovinmultiplatform

interface Platform {
    val name: String
    val nasaApiKey: String
}

expect fun getPlatform(): Platform