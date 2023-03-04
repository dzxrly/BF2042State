package com.eggtargaryen.bf2042state

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.eggtargaryen.bf2042state.navigation.AppNavigation
import com.eggtargaryen.bf2042state.navigation.Destinations
import com.eggtargaryen.bf2042state.page.StatePage
import com.eggtargaryen.bf2042state.ui.theme.BF2042StateTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        }
        setContent {
            BF2042StateTheme {
                AppNavigation(
                    startDestination = Destinations.STATE
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BF2042StateTheme {
        StatePage()
    }
}