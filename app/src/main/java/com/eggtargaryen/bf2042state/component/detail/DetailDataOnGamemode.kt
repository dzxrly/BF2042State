package com.eggtargaryen.bf2042state.component.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.component.TwoColumnBaseBadge
import com.eggtargaryen.bf2042state.model.Gamemode
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.utils.secondsToHours
import dev.esteki.expandable.Expandable

@Composable
fun DetailDataOnGamemode(
    playerInfoViewModel: PlayerInfoViewModel,
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
    val gamemodeDataList = playerInfo?.gamemodes ?: listOf()

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
                text = stringResource(id = R.string.state_map_mode_list_mode_name),
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
            gamemodeDataList.forEachIndexed { index, gamemodeData ->
                item {
                    Divider()
                    GamemodeDataDetailListItem(gamemodeItem = gamemodeData)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GamemodeDataDetailListItem(
    gamemodeItem: Gamemode
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
                    text = gamemodeItem.gamemodeName,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = gamemodeItem.wins.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = gamemodeItem.winPercent,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = "${secondsToHours(gamemodeItem.secondsPlayed)}小时",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.End
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_map_mode_list_kpm),
                    data_left = gamemodeItem.kpm.toString(),
                    label_right = stringResource(id = R.string.state_map_mode_list_kills),
                    data_right = gamemodeItem.kills.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_map_mode_list_sector_defend),
                    data_left = gamemodeItem.sectorDefend.toString(),
                    label_right = stringResource(id = R.string.state_map_mode_list_boom_deploy),
                    data_right = gamemodeItem.objectivesArmed.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_map_mode_list_boom_undeploy),
                    data_left = gamemodeItem.objectivesDisarmed.toString(),
                    label_right = stringResource(id = R.string.state_map_mode_list_boom_destroy),
                    data_right = gamemodeItem.objectivesDestroyed.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_map_mode_list_objective_defend),
                    data_left = gamemodeItem.objectivesDefended.toString(),
                    label_right = stringResource(id = R.string.state_map_mode_list_objective_capture),
                    data_right = gamemodeItem.objectivesCaptured.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_map_mode_list_objective_time),
                    data_left = "${secondsToHours(gamemodeItem.objetiveTime)}小时"
                )
            }
        }
    )
}
