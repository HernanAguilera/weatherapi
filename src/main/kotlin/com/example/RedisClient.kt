package com.example


class RedisClient {

    fun setItem(key: String, value: String) {
        val jedis = redis.clients.jedis.Jedis("redis://localhost:6379")
        try {
            jedis.set(key, value)
            println("Stored $key with value $value")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            jedis.close()
        }
    }

    fun getItem(key: String): String? {
        val jedis = redis.clients.jedis.Jedis("redis://localhost:6379")
        try {
            return jedis.get(key)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            jedis.close()
        }
        return null
    }
}