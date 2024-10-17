package com.example

import com.example.plugins.*
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    var redisClient = RedisClient()
    var wheaterClient = WeatherApi()
    GlobalScope.launch {
        while (true) {
            for (location in locations) {
                var tryCount = 0
                val maxTry = 2
                var isThereError = false
                do {
                    tryCount++
                    try {
                        var weather = wheaterClient.getDataFromApi(location)
                        redisClient.setItem(location.lowercase(), weather.main.temp.toString())
                    } catch (e: Exception) {
                        isThereError = true
                        var currentTime = DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                            .withZone(ZoneOffset.UTC)
                            .format(Instant.now())
                        redisClient.setItem("Error: $currentTime", e.message.toString())
                        println("Reintanando...")
                    }
                }while (isThereError && tryCount < maxTry)
            }
            delay(300000) //
        }
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    configureRouting()
}
