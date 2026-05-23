package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BuildConfig
import com.example.data.RetrofitClient
import com.example.data.WeatherForecastResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class WeatherUiState {
    object Initial : WeatherUiState()
    object Loading : WeatherUiState()
    data class Success(val response: WeatherForecastResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

class WeatherViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Initial)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()
    
    private val apiKey = BuildConfig.OPENWEATHER_API_KEY
    private val apiService = RetrofitClient.apiService

    init {
        // Initial load for a default city
        fetchWeather("London")
    }

    fun fetchWeather(city: String) {
        if (city.isBlank()) return
        
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                if (apiKey.isEmpty() || apiKey == "MY_OPENWEATHER_API_KEY") {
                    _uiState.value = WeatherUiState.Error("Missing OpenWeatherMap API Key. Please add it to Secrets.")
                    return@launch
                }
                
                val response = apiService.getFiveDayForecast(
                    query = city.trim(),
                    apiKey = apiKey
                )
                _uiState.value = WeatherUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.localizedMessage ?: "An error occurred fetching weather data.")
            }
        }
    }
}
