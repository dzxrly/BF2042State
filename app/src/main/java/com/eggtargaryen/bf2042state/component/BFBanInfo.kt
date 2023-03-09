package com.eggtargaryen.bf2042state.component

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.api.postPlayerBFBanStatus
import com.eggtargaryen.bf2042state.model.CheatInfo
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException


@Composable
fun labelToString(label: Int): String {
    return when (label) {
        -3 -> stringResource(id = R.string.state_bfban_info_loading)
        -2 -> stringResource(id = R.string.state_bfban_info_error)
        -1 -> stringResource(id = R.string.state_bfban_info_no_cheat)
        0 -> stringResource(id = R.string.state_bfban_info_status_0)
        1 -> stringResource(id = R.string.state_bfban_info_status_1)
        2 -> stringResource(id = R.string.state_bfban_info_status_2)
        3 -> stringResource(id = R.string.state_bfban_info_status_3)
        4 -> stringResource(id = R.string.state_bfban_info_status_4)
        5 -> stringResource(id = R.string.state_bfban_info_status_5)
        6 -> stringResource(id = R.string.state_bfban_info_status_6)
        7 -> stringResource(id = R.string.state_bfban_info_status_7)
        8 -> stringResource(id = R.string.state_bfban_info_status_8)
        else -> stringResource(id = R.string.state_bfban_info_error)
    }
}


@Composable
fun BFBanInfo(
    playerInfoViewModel: PlayerInfoViewModel,
) {
    val playerName = playerInfoViewModel.getPlayerInfo()?.userName ?: ""
    val localUriHandler = LocalUriHandler.current
    var label by remember {
        mutableStateOf(-3)
        // label = -2: error
        // label = -1: no cheat
        // label >= 0 == Status
    }
    var banUrl = remember {
        mutableStateOf("#")
    }

    if (playerName.isNotEmpty() && playerName != "") {
        postPlayerBFBanStatus(playerName)
            .enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    var banStatus = response.body?.string()
                    if (banStatus != null) {
                        banStatus = banStatus.substring(1 until banStatus.length - 1)
                        label = if (banStatus.isNotEmpty()) {
                            try {
                                val moshi = Moshi.Builder()
                                    .add(KotlinJsonAdapterFactory())
                                    .build()
                                val banStatusAdapter =
                                    moshi.adapter(CheatInfo::class.java)
                                val banStatusJson = banStatusAdapter.fromJson(banStatus)
                                println("${banStatusJson?.hacker}, ${banStatusJson?.status}")
                                if (banStatusJson?.hacker == true) {
                                    banUrl.value = banStatusJson.url!!
                                    banStatusJson.status!!
                                } else {
                                    banUrl.value = "#"
                                    -1
                                }
                            } catch (e: Exception) {
                                println(e)
                                banUrl.value = "#"
                                -2
                            }
                        } else {
                            banUrl.value = "#"
                            -2
                        }
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    banUrl.value = "#"
                    label = -2
                }
            })
        TextButton(
            onClick = {
                if (banUrl.value.isNotEmpty() && banUrl.value != "#") {
                    localUriHandler.openUri(banUrl.value)
                }
            },
            shape = MaterialTheme.shapes.large,
            colors = when (label) {
                1 -> ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.onError,
                )
                else -> ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.primary,
                )
            }
        ) {
            Text(text = labelToString(label = label))
        }
    }
}
