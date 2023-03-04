package com.eggtargaryen.bf2042state.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.component.BaseDataBadge
import com.eggtargaryen.bf2042state.utils.secondsToHours
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun StatePage(
//    playerInfoViewModel: PlayerInfoViewModel
) {
//    val playerInfo = playerInfoViewModel.getPlayerInfo()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val verticalScrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface),
        topBar = {
            TopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = verticalScrollState.value > 100,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("#")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "User Avatar",
                                placeholder = painterResource(id = R.drawable.round_account_box_24),
                                contentScale = ContentScale.Crop,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Player Name", maxLines = 1)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_back_ios_new_24),
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primary,
                elevation = 4.dp
            )
        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.surface)
                        .verticalScroll(verticalScrollState),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayerBaseInfoCard()
                    PlayerBaseDataCard()
                    DataDetailCard()
                }
            }
        }
    )
}


@Composable
fun PlayerBaseInfoCard(
//    playerInfo: PlayerInfo
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("#")
                        .crossfade(true)
                        .build(),
                    contentDescription = "User Avatar",
                    placeholder = painterResource(id = R.drawable.round_account_box_24),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Xx__Koraidon__xX",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "等级 Lv.350",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "游戏内时长 ${secondsToHours(1882410)}小时",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "最佳专家 Mackey",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        maxLines = 1
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // TODO Ban Status
        }
    }
}


@Composable
fun PlayerBaseDataCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                mainAxisSpacing = 12.dp,
                crossAxisSpacing = 12.dp,
            ) {
                BaseDataBadge(label = "K/D", data = "4.5")
                BaseDataBadge(label = "KPM", data = "1.5")
                BaseDataBadge(label = "DPM", data = "200.0")
                BaseDataBadge(label = "胜率", data = "83.83%")
                BaseDataBadge(label = "爆头率", data = "22.22%")
                BaseDataBadge(label = "准确度", data = "22.22%")
                BaseDataBadge(label = "击杀数", data = "50000")
                BaseDataBadge(label = "场均伤害", data = "5700.90")
                BaseDataBadge(label = "场均击杀", data = "43.43")
                BaseDataBadge(label = "游玩场数", data = "4343")
            }
        }
    }
}


@Composable
fun DataDetailCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {}
}
