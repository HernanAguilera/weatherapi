package com.example.plugins

import com.example.RedisClient
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(val temperature: String)

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/weather/{location}"){
            val location = call.parameters["location"]
            val redisClient = RedisClient()
            if (location == null) {
                call.respond(HttpStatusCode.BadRequest, "Location is required")
                return@get
            }
            val temp: String? = redisClient.getItem(location.lowercase())
            if (temp == null) {
                call.respond(HttpStatusCode.NotFound, "Location not found")
                return@get
            }
            var logRes =LocationResponse(temp)
            println(logRes)
            try {
                call.respond(logRes)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
