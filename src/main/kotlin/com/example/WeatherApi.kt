package com.example

import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

var locations = listOf<String>("Santiago", "Zurich", "Auckland", "Sidney", "London", "Georgia")

@Serializable
data class Weather(
    val coord: Coord,
    val weather: List<WeatherItem>,
    val main: Main
)

@Serializable
data class WeatherItem(
    val main: String,
    val description: String,
    val icon: String,
)

@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)

@Serializable
data class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Double
)

class WeatherApi {
    var apiKey = dotenv ()["API_WEATHER_KEY"]

    suspend fun getDataFromApi(location: String): Weather {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }

        return client.get("https://api.openweathermap.org/data/2.5/weather?q=${location}&appid=${this.apiKey}&units=metric")
            .body()
    }
}