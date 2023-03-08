package com.eggtargaryen.bf2042state.component.detail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel

@Composable
fun DetailDataOnGamemode(
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
    ) {}
}
