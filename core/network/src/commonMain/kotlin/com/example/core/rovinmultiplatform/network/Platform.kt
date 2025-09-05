package com.example.core.rovinmultiplatform.network

interface Platform {
    val name: String
    val nasaApiKey: String
}

expect fun getPlatform(): Platform
