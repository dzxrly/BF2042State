package com.eggtargaryen.bf2042state.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StatePage() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "State Page")
        }
    }
}
