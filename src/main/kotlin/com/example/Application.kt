package com.example

import com.example.plugins.*
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    var redisClient = RedisClient()
    var wheaterClient = WeatherApi()
    GlobalScope.launch {
        while (true) {
            for (location in locations) {
                var weather = wheaterClient.getDataFromApi(location)
                println(weather)
                redisClient.setItem(location.lowercase(), weather.main.temp.toString())
            }
            delay(30000) //
        }
    }
    configureRouting()
}
