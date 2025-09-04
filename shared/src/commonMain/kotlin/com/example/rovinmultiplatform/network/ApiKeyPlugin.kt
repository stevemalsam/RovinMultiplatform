package com.example.rovinmultiplatform.network

import io.ktor.client.plugins.api.createClientPlugin

val ApiKeyPlugin = createClientPlugin("ApiKeyPlugin", ::ApiKeyPluginConfig) {
    val apiKey = pluginConfig.apiKey
    val header = pluginConfig.header

    onRequest { request, _ ->
        request.url.parameters.append(header, apiKey)
    }
}

class ApiKeyPluginConfig {
    var apiKey: String = ""
    var header: String = "api_key"
}