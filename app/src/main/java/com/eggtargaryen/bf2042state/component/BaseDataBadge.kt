package com.eggtargaryen.bf2042state.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun BaseDataBadge(
    label: String,
    data: String,
) {
    Column(
        modifier = Modifier.width(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = data,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}
