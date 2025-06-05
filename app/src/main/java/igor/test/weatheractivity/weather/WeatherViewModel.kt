package igor.test.weatheractivity.weather

import android.content.Context
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import igor.test.weatheractivity.weather.weatherdata.ForecastResponse
import igor.test.weatheractivity.weather.weatherdata.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {
    private val _currentWeather = MutableStateFlow<WeatherResponse?>(null)
    val currentWeather: StateFlow<WeatherResponse?> = _currentWeather

    private val _forecast = MutableStateFlow<ForecastResponse?>(null)
    val forecast: StateFlow<ForecastResponse?> = _forecast

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(WeatherApi::class.java)
    private val apiKey = "3c1638f75aac1f81c22f95559768bb21"

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val current = api.getCurrentWeather(lat, lon, apiKey = apiKey)
                val forecast = api.getForecast(lat, lon, apiKey = apiKey)

                _currentWeather.value = current
                _forecast.value = forecast
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getLocation(context: Context) {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        fetchWeather(it.latitude, it.longitude)
                    } ?: run {
                        _error.value = "Не удалось получить местоположение"
                    }
                }
        } catch (e: SecurityException) {
            _error.value = "Нет разрешения на доступ к местоположению"
        }
    }
}