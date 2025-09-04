package com.example.rovinmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform