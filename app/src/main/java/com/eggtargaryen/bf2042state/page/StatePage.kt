@file:OptIn(ExperimentalPagerApi::class)

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.component.*
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.utils.secondsToHours
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun StatePage(
    onNavToLogin: () -> Unit = {},
    playerInfoViewModel: PlayerInfoViewModel
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
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
                                    .data(playerInfo?.avatar ?: "#")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "User Avatar",
                                placeholder = painterResource(id = R.drawable.round_account_box_24),
                                contentScale = ContentScale.Crop,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = playerInfo?.userName ?: "Unknown", maxLines = 1)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onNavToLogin() }) {
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
                    PlayerBaseInfoCard(playerInfoViewModel)
                    PlayerBaseDataCard(playerInfoViewModel)
                    DataDetailCard(playerInfoViewModel)
                }
            }
        }
    )
}


@Composable
fun PlayerBaseInfoCard(
    playerInfoViewModel: PlayerInfoViewModel
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
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
                        .data(playerInfo?.avatar ?: "#")
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
                        text = playerInfo?.userName ?: "Unknown",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = "等级 Lv.$playerInfo.",
//                        style = MaterialTheme.typography.body1,
//                        color = MaterialTheme.colors.secondary,
//                        maxLines = 1
//                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${secondsToHours(playerInfo?.secondsPlayed ?: -1)}小时",
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
fun PlayerBaseDataCard(
    playerInfoViewModel: PlayerInfoViewModel
) {
    val playerInfo = playerInfoViewModel.getPlayerInfo()
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
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_kd),
                    data = playerInfo?.infantryKillDeath.toString() ?: "0.0"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_kpm),
                    data = playerInfo?.killsPerMinute.toString() ?: "0.0"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_dpm),
                    data = playerInfo?.damagePerMinute.toString() ?: "0.0"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_win_rate),
                    data = playerInfo?.winPercent ?: "0.0%"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_hs_rate),
                    data = playerInfo?.headshots ?: "0.0%"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_acc),
                    data = playerInfo?.accuracy ?: "0.0%"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_kills),
                    data = playerInfo?.kills.toString() ?: "0"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_dpM),
                    data = playerInfo?.damagePerMatch.toString() ?: "0.0"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_kpM),
                    data = playerInfo?.killsPerMatch.toString() ?: "0.0"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_card_M),
                    data = playerInfo?.matchesPlayed.toString() ?: "0"
                )
                BaseDataBadge(
                    label = stringResource(id = R.string.state_base_data_best_class),
                    data = playerInfo?.bestClass ?: "Unknown"
                )
            }
        }
    }
}


@Composable
fun DataDetailCard(
    playerInfoViewModel: PlayerInfoViewModel,
    initPageIndex: Int = 0
) {
    val pagerState = rememberPagerState(initPageIndex)
    val coroutineScope = rememberCoroutineScope()

    val detailDataTabContentList = listOf(
        stringResource(id = R.string.state_tab_foot),
        stringResource(id = R.string.state_tab_weapon),
        stringResource(id = R.string.state_tab_tank),
        stringResource(id = R.string.state_tab_plane),
        stringResource(id = R.string.state_tab_gadget)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScrollableTabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primary,
                divider = { },
            ) {
                detailDataTabContentList.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                count = detailDataTabContentList.size,
                state = pagerState,
                verticalAlignment = Alignment.Top,
            ) { page ->
                when (page) {
                    0 -> DetailDataOnFoot(playerInfoViewModel)
                    1 -> DetailDataOnWeapon(playerInfoViewModel)
                    2 -> DetailDataOnTank(playerInfoViewModel)
                    3 -> DetailDataOnPlane(playerInfoViewModel)
                    4 -> DetailDataOnGadget(playerInfoViewModel)
                }
            }
        }
    }
}
