package igor.test.weatheractivity.appui.onday

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import igor.test.weatheractivity.weather.weatherdata.WeatherResponse

@Composable
fun InfoOnDayActivity(weather: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 144.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Прогноз погоды")
        Text("temp = ${weather.main.temp}")
        Text("latitude = ${weather.coord.lat}")
        Text("longitude = ${weather.coord.lon}")
        Text("humidity = ${weather.main.humidity}")
    }
}