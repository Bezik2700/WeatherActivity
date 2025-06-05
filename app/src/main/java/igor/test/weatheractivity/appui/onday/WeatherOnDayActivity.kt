package igor.test.weatheractivity.appui.onday

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import igor.test.weatheractivity.appui.ErrorMessage
import igor.test.weatheractivity.appui.Screens
import igor.test.weatheractivity.weather.WeatherViewModel

@Composable
fun WeatherOnDayActivity(
    navController: NavController,
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier,
    onRequestPermission: () -> Unit = {}
) {
    val currentWeather by viewModel.currentWeather.collectAsState()
    val forecast by viewModel.forecast.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                ErrorMessage(error = error!!, onRetry = onRequestPermission)
            }
            currentWeather != null && forecast != null -> {
                InfoOnDayActivity(currentWeather!!)
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screens.ForecastScreen.route)}) {
            Text("forecast on week ->")
        }
    }
}