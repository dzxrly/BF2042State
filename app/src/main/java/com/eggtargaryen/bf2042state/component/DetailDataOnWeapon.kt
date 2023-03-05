package com.eggtargaryen.bf2042state.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R


@Composable
fun DetailDataOnWeapon() {
    val (gesturesEnabled, toggleGesturesEnabled) = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // List Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_detail_list_weapon_name),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_detail_list_kills),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_detail_list_kpm),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_detail_list_time),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End
            )
        }
        // List Content
    }
}
