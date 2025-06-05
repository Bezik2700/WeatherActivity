package igor.test.weatheractivity.weather.weatherdata

data class WeatherResponse(
    val coord: Coordinates,
    val weather: List<WeatherDescription>,
    val main: MainWeatherData,
    val wind: WindData,
    val name: String,
    val dt: Long
)

data class ForecastResponse(
    val list: List<ForecastItem>,
    val city: City
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)

data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainWeatherData(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

data class WindData(
    val speed: Double,
    val deg: Int
)

data class ForecastItem(
    val dt: Long,
    val main: MainWeatherData,
    val weather: List<WeatherDescription>,
    val dt_txt: String
)

data class City(
    val name: String,
    val country: String
)

data class DailyForecastItem(
    val date: String,
    val dayTemp: Int,
    val nightTemp: Int,
    val description: String,
    val icon: String
)