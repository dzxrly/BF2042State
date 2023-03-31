package com.eggtargaryen.bf2042state.page

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.api.getPlayerId
import com.eggtargaryen.bf2042state.api.getPlayerStateByPlayerId
import com.eggtargaryen.bf2042state.api.getPlayerStateByPlayerName
import com.eggtargaryen.bf2042state.api.searchPlayerByName
import com.eggtargaryen.bf2042state.component.About
import com.eggtargaryen.bf2042state.component.CustomSnackBar
import com.eggtargaryen.bf2042state.component.RoundOutlineTextField
import com.eggtargaryen.bf2042state.component.SnackBarType
import com.eggtargaryen.bf2042state.model.PlayerId
import com.eggtargaryen.bf2042state.model.PlayerInfo
import com.eggtargaryen.bf2042state.model.PlayerInfoViewModel
import com.eggtargaryen.bf2042state.model.PlayerQueryList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException


@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalMaterialApi
@Composable
fun LoginPage(
    onNavToState: () -> Unit = {},
    playerInfoViewModel: PlayerInfoViewModel
) {
    val platformLabelOptions =
        listOf("PC", "Xbox One", "Xbox Series", "PlayStation 4", "PlayStation 5")
    val platformValOptions = listOf("pc", "xboxone", "xboxseries", "ps4", "ps5")
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val internetPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.INTERNET)

    var username by remember { mutableStateOf("") }
    var playerId by remember { mutableStateOf(0L) }
    var platformSelectorExpanded by remember { mutableStateOf(false) }
    var playerNameSelectorExpanded by remember { mutableStateOf(false) }
    var selectedPlatformText by remember { mutableStateOf("") }
    var selectedPlatform by remember { mutableStateOf("") }
    var queryBtnLoadingState by remember { mutableStateOf(false) }
    var searchTextFieldLoadingState by remember { mutableStateOf(false) }
    var enableQueryEnhance by remember { mutableStateOf(false) }
    var queryList by remember { mutableStateOf(PlayerQueryList()) }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                CustomSnackBar(snackBarType = SnackBarType.ERROR, data.message)
            }
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        content = { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {
                SideEffect {
                    if (!internetPermissionState.status.isGranted) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "需要网络权限！"
                            )
                        }
                        internetPermissionState.launchPermissionRequest()
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 32.dp, end = 32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bf_2042_white_nav_logo),
                            contentDescription = "Battlefield 2042 Logo",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(id = R.string.login_logo_slogan),
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.background,
                            letterSpacing = 1.5.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colors.primary)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    ExposedDropdownMenuBox(
                        expanded = platformSelectorExpanded,
                        onExpandedChange = { platformSelectorExpanded = !platformSelectorExpanded },
                    ) {
                        RoundOutlineTextField(
                            value = selectedPlatformText,
                            onValueChange = { },
                            label = { Text(stringResource(id = R.string.login_label_platform)) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = platformSelectorExpanded
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.round_videogame_asset_24),
                                    contentDescription = stringResource(id = R.string.login_label_platform),
                                    tint = MaterialTheme.colors.primary
                                )
                            },
                            readOnly = true,
                        )
                        ExposedDropdownMenu(
                            expanded = platformSelectorExpanded,
                            onDismissRequest = {
                                platformSelectorExpanded = false
                            },
                            modifier = Modifier
                                .background(color = MaterialTheme.colors.onSecondary)
                        ) {
                            platformLabelOptions.forEachIndexed { platformIndex, selectionOption ->
                                DropdownMenuItem(onClick = {
                                    selectedPlatformText = selectionOption
                                    selectedPlatform = platformValOptions[platformIndex]
                                    platformSelectorExpanded = false
                                }) {
                                    Text(text = selectionOption)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    ExposedDropdownMenuBox(
                        expanded = playerNameSelectorExpanded,
                        onExpandedChange = {
                            playerNameSelectorExpanded = !playerNameSelectorExpanded
                        },
                    ) {
                        RoundOutlineTextField(
                            value = username,
                            onValueChange = {
                                username = it
                                if (selectedPlatform.isNotEmpty() && it.isNotEmpty()) {
                                    searchTextFieldLoadingState = true
                                    searchPlayerByName(
                                        username,
                                        selectedPlatform
                                    ).enqueue(object : Callback {
                                        override fun onResponse(call: Call, response: Response) {
                                            val playerQueryList = response.body?.string()
                                            println(playerQueryList)
                                            try {
                                                val moshi = Moshi.Builder()
                                                    .add(KotlinJsonAdapterFactory())
                                                    .build()
                                                val playerQueryListJson =
                                                    moshi.adapter(PlayerQueryList::class.java)
                                                        .fromJson(playerQueryList!!)
                                                if (playerQueryListJson?.results != null && playerQueryListJson.results.isNotEmpty()) {
                                                    queryList = playerQueryListJson
                                                    playerNameSelectorExpanded = true
                                                }
                                                searchTextFieldLoadingState = false
                                            } catch (e: Exception) {
                                                searchTextFieldLoadingState = false
                                                println(e)
                                            }
                                        }

                                        override fun onFailure(call: Call, e: IOException) {
                                            searchTextFieldLoadingState = false
                                        }
                                    })
                                }
                            },
                            label = { Text(text = stringResource(id = R.string.login_label_username)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    contentDescription = stringResource(id = R.string.login_label_username),
                                    tint = MaterialTheme.colors.primary
                                )
                            },
                            trailingIcon = {
                                if (searchTextFieldLoadingState) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colors.primary,
                                        modifier = Modifier.size(16.dp),
                                        strokeWidth = 2.dp
                                    )
                                }
                            },
                        )
                        if (queryList.results != null && queryList.results!!.isNotEmpty()) {
                            ExposedDropdownMenu(
                                expanded = playerNameSelectorExpanded,
                                onDismissRequest = { playerNameSelectorExpanded = false },
                                modifier = Modifier
                                    .background(color = MaterialTheme.colors.onSecondary)
                            ) {
                                queryList.results?.forEach { player ->
                                    DropdownMenuItem(onClick = {
                                        username = player.name.toString()
                                        playerNameSelectorExpanded = false
                                    }) {
                                        player.name?.let { Text(text = it) }
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            queryBtnLoadingState = true
                            // Check if username and platform is empty
                            if (username.isNotEmpty() && selectedPlatform.isNotEmpty()) {
                                if (enableQueryEnhance) {
                                    getPlayerId(
                                        username
                                    ).enqueue(object : Callback {
                                        override fun onResponse(call: Call, response: Response) {
                                            val playerIdRes = response.body?.string()
                                            println(playerIdRes)
                                            try {
                                                val moshi = Moshi.Builder()
                                                    .add(KotlinJsonAdapterFactory())
                                                    .build()
                                                val playerIdResAdapter =
                                                    moshi.adapter(PlayerId::class.java)
                                                val playerIdResJson =
                                                    playerIdResAdapter.fromJson(playerIdRes)
                                                playerId =
                                                    playerIdResJson?.results?.get(0)?.nucleusID
                                                        ?: 0L
                                                getPlayerStateByPlayerId(
                                                    playerId,
                                                    selectedPlatform,
                                                ).enqueue(object : Callback {
                                                    override fun onResponse(
                                                        call: Call,
                                                        response: Response
                                                    ) {
                                                        val playerInfo = response.body?.string()
                                                        try {
                                                            val moshi = Moshi.Builder()
                                                                .add(KotlinJsonAdapterFactory())
                                                                .build()
                                                            val playerInfoAdapter =
                                                                moshi.adapter(PlayerInfo::class.java)
                                                            val playerInfoJson =
                                                                playerInfoAdapter.fromJson(
                                                                    playerInfo
                                                                )
                                                            playerInfoViewModel.postPlayerInfo(
                                                                playerInfoJson!!
                                                            )
                                                            scope.launch { onNavToState() }
                                                        } catch (e: Exception) {
                                                            println(e)
                                                            scope.launch {
                                                                scaffoldState.snackbarHostState.showSnackbar(
                                                                    "未找到该用户！"
                                                                )
                                                            }
                                                        }
                                                        queryBtnLoadingState = false
                                                    }

                                                    override fun onFailure(
                                                        call: Call,
                                                        e: IOException
                                                    ) {
                                                        scope.launch {
                                                            scaffoldState.snackbarHostState.showSnackbar(
                                                                "网络连接异常，请重试。"
                                                            )
                                                        }
                                                        queryBtnLoadingState = false
                                                    }
                                                })
                                            } catch (e: Exception) {
                                                queryBtnLoadingState = false
                                                println(e)
                                                scope.launch {
                                                    scaffoldState.snackbarHostState.showSnackbar(
                                                        "未找到该用户！"
                                                    )
                                                }
                                            }
                                        }

                                        override fun onFailure(call: Call, e: IOException) {
                                            scope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar(
                                                    "网络连接异常，请重试。"
                                                )
                                            }
                                            queryBtnLoadingState = false
                                        }
                                    })
                                } else {
                                    getPlayerStateByPlayerName(
                                        username,
                                        selectedPlatform,
                                    ).enqueue(object : Callback {
                                        override fun onResponse(
                                            call: Call,
                                            response: Response
                                        ) {
                                            val playerInfo = response.body?.string()
                                            try {
                                                val moshi = Moshi.Builder()
                                                    .add(KotlinJsonAdapterFactory())
                                                    .build()
                                                val playerInfoAdapter =
                                                    moshi.adapter(PlayerInfo::class.java)
                                                val playerInfoJson =
                                                    playerInfoAdapter.fromJson(playerInfo)
                                                playerInfoViewModel.postPlayerInfo(
                                                    playerInfoJson!!
                                                )
                                                scope.launch { onNavToState() }
                                            } catch (e: Exception) {
                                                println(e)
                                                scope.launch {
                                                    scaffoldState.snackbarHostState.showSnackbar(
                                                        "未找到该用户！可以尝试开启增强查询"
                                                    )
                                                }
                                            }
                                            queryBtnLoadingState = false
                                        }

                                        override fun onFailure(call: Call, e: IOException) {
                                            scope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar(
                                                    "网络连接异常，请重试。"
                                                )
                                            }
                                            queryBtnLoadingState = false
                                        }
                                    })
                                }
                            } else {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        "用户名或平台不能为空！"
                                    )
                                }
                                queryBtnLoadingState = false
                            }
                        },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = MaterialTheme.colors.background,
                            disabledBackgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.32f)
                        ),
                        enabled = !queryBtnLoadingState
                    ) {
                        Crossfade(targetState = queryBtnLoadingState) { loading ->
                            when (loading) {
                                true -> Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp),
                                        color = MaterialTheme.colors.primary,
                                        strokeWidth = 3.dp,
                                    )
                                }
                                false -> Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.round_manage_search_24),
                                        contentDescription = stringResource(id = R.string.login_label_search_btn),
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = stringResource(id = R.string.login_label_search_btn),
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            checked = enableQueryEnhance,
                            onCheckedChange = { enableQueryEnhance = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colors.primary,
                                checkedTrackColor = MaterialTheme.colors.primary.copy(alpha = 0.32f),
                                uncheckedThumbColor = MaterialTheme.colors.background,
                                uncheckedTrackColor = MaterialTheme.colors.background.copy(alpha = 0.32f),
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.login_query_enable),
                            style = MaterialTheme.typography.body2,
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                }
            }
        },
        drawerContent = {
            About(
                onClose = {
                    scope.launch { scaffoldState.drawerState.close() }
                }
            )
        },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerShape = RoundedCornerShape(topEnd = 19.dp, bottomEnd = 19.dp),
    )
}
