package com.example.data

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {
    @GET("data/2.5/forecast")
    suspend fun getFiveDayForecast(
        @Query("q") query: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherForecastResponse
}
