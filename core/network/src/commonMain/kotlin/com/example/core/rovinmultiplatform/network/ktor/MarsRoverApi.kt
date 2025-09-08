package com.example.core.rovinmultiplatform.network.ktor

import com.example.core.rovinmultiplatform.network.getPlatform
import com.example.core.rovinmultiplatform.network.model.JsonResponse
import com.example.core.rovinmultiplatform.network.model.NetworkPhoto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "api.nasa.gov"

class MarsRoverApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(ApiKeyPlugin) {
            apiKey = getPlatform().nasaApiKey
        }
    }

    suspend fun getPhotos(sol: Int, page: Int): List<NetworkPhoto> {
        return httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("mars-photos/api/v1/rovers/curiosity/photos")
                parameters.append("sol", "$sol")
                parameters.append("page", "$page")
            }
        }.body<JsonResponse>().photos
    }
}