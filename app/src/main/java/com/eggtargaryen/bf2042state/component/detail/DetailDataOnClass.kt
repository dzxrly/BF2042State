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
import com.eggtargaryen.bf2042state.model.PurpleClass
import com.eggtargaryen.bf2042state.utils.secondsToHours
import dev.esteki.expandable.Expandable


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailDataOnClass(
    playerInfoViewModel: PlayerInfoViewModel
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
    val classDataList = playerInfo?.classes ?: listOf()

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
                text = stringResource(id = R.string.state_detail_list_class_name),
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
            classDataList.forEachIndexed { index, classData ->
                item {
                    Divider()
                    ClassDataDetailListItem(classItem = classData)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClassDataDetailListItem(
    classItem: PurpleClass
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
                    text = classItem.characterName ?: "Unknown",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = classItem.kills.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = classItem.kpm.toString(),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = "${secondsToHours(classItem.secondsPlayed ?: 0L)}小时",
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
                    data_left = classItem.kpm.toString(),
                    label_right = stringResource(id = R.string.state_detail_list_kd),
                    data_right = classItem.killDeath.toString()
                )
                TwoColumnBaseBadge(
                    label_left = stringResource(id = R.string.state_detail_list_class_type),
                    data_left = classItem.className ?: "Unknown"
                )
            }
        }
    )
}
