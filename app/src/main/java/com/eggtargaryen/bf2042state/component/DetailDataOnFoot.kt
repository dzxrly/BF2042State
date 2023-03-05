package com.eggtargaryen.bf2042state.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R


@Composable
fun DetailRow(
    label: String,
    data: String
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = label,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = data,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1
        )
    }
}


@Composable
fun DetailDataOnFoot() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_kills),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_death),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_assist),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_mvp),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_wins),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_loses),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_hs),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_dmg),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_fire),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_vehicle_destroy),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_enemies_spot),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_revives),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_heals),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_resupplies),
            data = "1111"
        )
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_repairs),
            data = "1111"
        )
    }
}
