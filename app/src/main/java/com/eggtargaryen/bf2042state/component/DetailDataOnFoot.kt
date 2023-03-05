package com.eggtargaryen.bf2042state.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel


@Composable
fun DetailRow(
    label: String,
    data: String,
    showDivider: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = data,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1
        )
    }
}


@Composable
fun DetailDataOnFoot(
    playerInfoViewModel: PlayerInfoViewModel
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_kills),
            data = playerInfo?.kills.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_death),
            data = playerInfo?.deaths.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_assist),
            data = playerInfo?.killAssists.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_mvp),
            data = playerInfo?.mvp.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_wins),
            data = playerInfo?.wins.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_loses),
            data = playerInfo?.loses.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_hs),
            data = playerInfo?.headShots.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_dmg),
            data = playerInfo?.damage.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_fire),
            data = playerInfo?.shotsFired.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_hit),
            data = playerInfo?.shotsHit.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_vehicle_destroy),
            data = playerInfo?.vehiclesDestroyed.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_enemies_spot),
            data = playerInfo?.enemiesSpotted.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_revives),
            data = playerInfo?.revives.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_heals),
            data = playerInfo?.heals.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_resupplies),
            data = playerInfo?.resupplies.toString() ?: "0"
        )
        Divider()
        DetailRow(
            label = stringResource(id = R.string.state_detail_data_on_foot_repairs),
            data = playerInfo?.repairs.toString() ?: "0"
        )
    }
}
