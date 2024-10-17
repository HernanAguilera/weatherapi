package com.example

import io.github.cdimascio.dotenv.dotenv
import kotlinx.serialization.Serializable

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

    var locations = listOf<String>("Santiago", "Zurich", "Auckland", "Sidney", "London", "Georgia")

    var apiKey = dotenv ()["API_WEATHER_KEY"]

    suspend fun getDataFromApi(location: String): Weather {
        println("location $location")
        return Weather(Coord(0.0,0.0), listOf(), Main(0.0, 0.0, 0.0))
    }
}