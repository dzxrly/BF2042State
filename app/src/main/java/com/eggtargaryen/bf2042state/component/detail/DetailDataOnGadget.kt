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
import com.eggtargaryen.bf2042state.model.Gadget
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.utils.numberFormat
import dev.esteki.expandable.Expandable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailDataOnGadget(
    playerInfoViewModel: PlayerInfoViewModel
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
    val gadgetDataList = playerInfo?.gadgets ?: listOf()

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
                text = stringResource(id = R.string.state_detail_list_gadget_name),
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
                text = stringResource(id = R.string.state_detail_list_use_count),
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
            gadgetDataList.forEachIndexed { index, gadgetData ->
                item {
                    Divider()
                    GadgetDataDetailListItem(gadgetItem = gadgetData)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GadgetDataDetailListItem(
    gadgetItem: Gadget
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
                    text = gadgetItem.gadgetName ?: "Unknown",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = gadgetItem.kills.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = gadgetItem.kpm.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = gadgetItem.uses.toString(),
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
                    data_left = gadgetItem.kpm.toString(),
                    stringResource(id = R.string.state_detail_list_dpm),
                    gadgetItem.dpm.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_detail_list_dmg),
                    data_left = numberFormat(gadgetItem.damage ?: 0L),
                    stringResource(id = R.string.state_detail_list_be_multi_kills),
                    gadgetItem.multiKills.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_detail_list_destroy_to),
                    data_left = gadgetItem.vehiclesDestroyedWith.toString()
                )
            }
        }
    )
}

