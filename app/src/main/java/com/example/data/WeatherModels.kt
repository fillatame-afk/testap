package com.example.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastResponse(
    @Json(name = "list") val list: List<ForecastItem>,
    @Json(name = "city") val city: CityInfo
)

@JsonClass(generateAdapter = true)
data class ForecastItem(
    @Json(name = "dt") val dt: Long,
    @Json(name = "main") val main: MainInfo,
    @Json(name = "weather") val weather: List<WeatherInfo>,
    @Json(name = "wind") val wind: WindInfo,
    @Json(name = "dt_txt") val dtTxt: String
)

@JsonClass(generateAdapter = true)
data class MainInfo(
    @Json(name = "temp") val temp: Double,
    @Json(name = "temp_min") val tempMin: Double,
    @Json(name = "temp_max") val tempMax: Double,
    @Json(name = "humidity") val humidity: Int
)

@JsonClass(generateAdapter = true)
data class WeatherInfo(
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

@JsonClass(generateAdapter = true)
data class WindInfo(
    @Json(name = "speed") val speed: Double
)

@JsonClass(generateAdapter = true)
data class CityInfo(
    @Json(name = "name") val name: String,
    @Json(name = "country") val country: String
)
