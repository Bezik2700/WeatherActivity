package igor.test.weatheractivity.appui.onday

import android.Manifest
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import igor.test.weatheractivity.appui.Screens
import igor.test.weatheractivity.weather.WeatherViewModel

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
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

    WeatherOnDayActivity(
        navController = navController,
        viewModel = viewModel,
        modifier = Modifier.padding(),
        onRequestPermission = {
            locationPermissionState.launchPermissionRequest()
        }
    )
    BackHandler {}
}