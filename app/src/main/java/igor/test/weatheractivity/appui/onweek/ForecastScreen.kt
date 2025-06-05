package igor.test.weatheractivity.appui.onweek

import android.Manifest
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import igor.test.weatheractivity.appui.ErrorMessage
import igor.test.weatheractivity.appui.Screens
import igor.test.weatheractivity.weather.WeatherViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ForecastScreen(
    viewModel: WeatherViewModel = viewModel(),
    navController: NavController
){

    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        if (locationPermissionState.status.isGranted) {
            viewModel.getLocation(context)
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    WeatherActivityWeek(
        navController = navController,
        viewModel = viewModel,
        onRequestPermission = {
            locationPermissionState.launchPermissionRequest()
        }
    )
    BackHandler {
        navController.navigate(Screens.MainScreen.route)
    }
}