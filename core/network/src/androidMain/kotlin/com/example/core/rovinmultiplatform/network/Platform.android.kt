package com.example.core.rovinmultiplatform.network

import android.os.Build
import com.example.rovinmultiplatform.core.network.BuildConfig

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val nasaApiKey: String = BuildConfig.nasaApiKey
}

actual fun getPlatform(): Platform = AndroidPlatform()