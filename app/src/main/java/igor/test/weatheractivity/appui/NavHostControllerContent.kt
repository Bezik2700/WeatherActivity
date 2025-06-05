package igor.test.weatheractivity.appui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import igor.test.weatheractivity.MainActivity
import igor.test.weatheractivity.appui.onday.MainScreen
import igor.test.weatheractivity.appui.onweek.ForecastScreen
import kotlin.system.exitProcess

sealed class Screens(val route: String){
    data object MainScreen: Screens("MainScreen")
    data object ForecastScreen: Screens("ForecastScreen")
}

@Composable
fun NavHostControllerContent(navController: NavHostController){

    Box(modifier = Modifier){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth().padding(32.dp)
        ) {
            IconButton(onClick = {
                MainActivity().finish()
                exitProcess(0)
            }) {
                Icon(Icons.Default.ExitToApp, contentDescription = null)
            }
        }
        NavHost(
            navController = navController,
            startDestination = Screens.MainScreen.route
        ) {
            composable (route = Screens.MainScreen.route) {
                MainScreen(navController = navController)
            }
            composable (route = Screens.ForecastScreen.route) {
                ForecastScreen(navController = navController)
            }
        }
    }
}

@Composable
fun ErrorMessage(error: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onRetry) {
            Text("Повторить попытку")
        }
    }
}