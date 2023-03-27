package com.eggtargaryen.bf2042state.component.detail

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
import com.eggtargaryen.bf2042state.component.TwoColumnBaseBadge
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.model.Weapon
import com.eggtargaryen.bf2042state.utils.secondsToHours
import dev.esteki.expandable.Expandable


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailDataOnWeapon(
    playerInfoViewModel: PlayerInfoViewModel,
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
    val weaponDataList = playerInfo?.weapons ?: listOf()

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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // List Content
            weaponDataList.forEachIndexed { _, weaponData ->
                item {
                    Divider()
                    WeaponDataDetailListItem(
                        weaponItem = weaponData,
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeaponDataDetailListItem(
    weaponItem: Weapon
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
                    text = weaponItem.weaponName ?: "Unknown",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = weaponItem.kills.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = weaponItem.killsPerMinute.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = "${secondsToHours(weaponItem.timeEquipped ?: 0L)}小时",
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
                    label_left = stringResource(id = R.string.state_detail_list_kpm),
                    data_left = weaponItem.killsPerMinute.toString(),
                    label_right = stringResource(id = R.string.state_detail_list_hsr),
                    data_right = weaponItem.headshots ?: "0.0%"
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_detail_list_accuracy),
                    data_left = weaponItem.accuracy ?: "0.0%",
                    stringResource(id = R.string.state_detail_list_dpm),
                    weaponItem.damagePerMinute.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_detail_list_dmg),
                    data_left = weaponItem.damage.toString(),
                    label_right = stringResource(id = R.string.state_detail_list_be_multi_kills),
                    data_right = weaponItem.multiKills.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_detail_list_shot_count),
                    data_left = weaponItem.shotsFired.toString(),
                    label_right = stringResource(id = R.string.state_detail_list_hit_count),
                    data_right = weaponItem.shotsHit.toString()
                )
            }
        }
    )
}
