package com.eggtargaryen.bf2042state.component.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.utils.secondsToHours


@Composable
fun DetailDataOnMap(
    playerInfoViewModel: PlayerInfoViewModel,
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
    val mapDataList = playerInfo?.maps ?: listOf()

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
                text = stringResource(id = R.string.state_map_mode_list_map_name),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_map_mode_list_win),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_map_mode_list_winr),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.weight(0.25f),
                text = stringResource(id = R.string.state_map_mode_list_time),
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
            mapDataList.forEachIndexed { _, mapData ->
                item {
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(0.25f),
                            text = mapData.mapName ?: "Unknown",
                            color = MaterialTheme.colors.secondary,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier.weight(0.25f),
                            text = mapData.wins.toString(),
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.weight(0.25f),
                            text = mapData.winPercent ?: "0.0%",
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.weight(0.25f),
                            text = "${secondsToHours(mapData.secondsPlayed ?: 0L)}小时",
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}