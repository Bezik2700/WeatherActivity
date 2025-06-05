package igor.test.weatheractivity.appui.onweek

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.test.weatheractivity.weather.weatherdata.DailyForecastItem
import igor.test.weatheractivity.weather.weatherdata.ForecastResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherOnWeekActivity(forecast: ForecastResponse) {

    val dailyForecasts = forecast.list
        .groupBy { it.dt_txt.substring(0, 10) }
        .map { entry ->
            val day = entry.value.first()
            DailyForecastItem(
                date = SimpleDateFormat("EEEE", Locale.getDefault())
                    .format(Date(day.dt * 1000)),
                dayTemp = day.main.temp_max.toInt(),
                nightTemp = day.main.temp_min.toInt(),
                description = day.weather[0].description,
                icon = day.weather[0].icon
            )
        }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Forecast on Week",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn {
            items(dailyForecasts) { item ->
                InfoOnWeekActivity(item)
                Divider()
            }
        }
    }
}
