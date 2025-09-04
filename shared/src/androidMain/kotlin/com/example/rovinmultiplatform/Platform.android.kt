package com.example.rovinmultiplatform

import android.os.Build
import com.example.rovinmultiplatform.shared.BuildConfig

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val nasaApiKey: String = BuildConfig.nasaApiKey
}

actual fun getPlatform(): Platform = AndroidPlatform()