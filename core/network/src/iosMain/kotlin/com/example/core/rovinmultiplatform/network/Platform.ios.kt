package com.example.core.rovinmultiplatform.network

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.dictionaryWithContentsOfFile
import platform.UIKit.UIDevice

class IOSPlatform: Platform
{
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val nasaApiKey: String
        get() = getStringResource(
            filename = "Keys",
            fileType = "plist",
            valueKey = "nasaApiKey"
        ) ?: ""
}

internal fun getStringResource(
    filename: String,
    fileType: String,
    valueKey: String,
): String? {
    val result = NSBundle.mainBundle.pathForResource(filename, fileType)?.let {
        val map = NSDictionary.dictionaryWithContentsOfFile(it)
        map?.get(valueKey) as? String
    }
    return result
}

actual fun getPlatform(): Platform = IOSPlatform()