package igor.test.weatheractivity.appui.onweek

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.test.weatheractivity.weather.weatherdata.DailyForecastItem

@Composable
fun InfoOnWeekActivity(forecast: DailyForecastItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = forecast.date,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "${forecast.dayTemp}° / ${forecast.nightTemp}°",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}