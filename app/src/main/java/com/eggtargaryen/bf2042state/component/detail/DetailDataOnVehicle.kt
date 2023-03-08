package com.eggtargaryen.bf2042state.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.model.Vehicle
import com.eggtargaryen.bf2042state.utils.secondsToHours
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import dev.esteki.expandable.Expandable


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailDataOnVehicle(
    playerInfoViewModel: PlayerInfoViewModel
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
    val vehicleDataList = playerInfo?.vehicles ?: listOf()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // List Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_detail_list_vehicle_name),
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // List Content
            vehicleDataList.forEachIndexed { index, vehicleData ->
                item {
                    Divider()
                    VehicleDataDetailListItem(vehicleData)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VehicleDataDetailListItem(
    vehicleData: Vehicle
) {
    val expanded = remember {
        mutableStateOf(false)
    }

    Expandable(
        expanded = expanded.value,
        onExpandChanged = {
            expanded.value = it
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = vehicleData.vehicleName,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = vehicleData.kills.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = vehicleData.killsPerMinute.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = "${secondsToHours(vehicleData.timeIn)}小时",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.End
                )
            }
        },
        content = {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
            ) {
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_kpm),
                    data = vehicleData.killsPerMinute.toString()
                )
                Spacer(modifier = Modifier.size(2.dp))
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_dmg),
                    data = vehicleData.damage.toString()
                )
                Spacer(modifier = Modifier.size(2.dp))
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_be_driver_assists),
                    data = vehicleData.driverAssists.toString()
                )
                Spacer(modifier = Modifier.size(2.dp))
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_destroy_to),
                    data = vehicleData.vehiclesDestroyedWith.toString()
                )
                Spacer(modifier = Modifier.size(2.dp))
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_be_destroy),
                    data = vehicleData.destroyed.toString()
                )
                Spacer(modifier = Modifier.size(2.dp))
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_be_multi_kills),
                    data = vehicleData.multiKills.toString()
                )
                Spacer(modifier = Modifier.size(2.dp))
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_dis),
                    data = vehicleData.distanceTraveled.toString()
                )
                Spacer(modifier = Modifier.size(2.dp))
                BaseDataBadgeRow(
                    label = stringResource(id = R.string.state_detail_list_be_road_kill),
                    data = vehicleData.roadKills.toString()
                )
            }
        }
    )
}
