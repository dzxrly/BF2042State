package com.eggtargaryen.bf2042state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.eggtargaryen.bf2042state.navigation.AppNavigation
import com.eggtargaryen.bf2042state.navigation.Destinations
import com.eggtargaryen.bf2042state.ui.theme.BF2042StateTheme
import com.funny.data_saver.core.DataSaverPreferences
import com.funny.data_saver.core.LocalDataSaver
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BF2042StateTheme {
                val systemUiController = rememberSystemUiController()
                val dataSaverPreferences = DataSaverPreferences(applicationContext)

                systemUiController.setSystemBarsColor(
                    MaterialTheme.colors.background,
                    darkIcons = false
                )
                CompositionLocalProvider(LocalDataSaver provides dataSaverPreferences) {
                    AppNavigation(
                        startDestination = Destinations.LOGIN
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BF2042StateTheme {
//        StatePage()
    }
}