package com.eggtargaryen.bf2042state.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import com.eggtargaryen.bf2042state.BuildConfig
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.api.updateCheck
import com.eggtargaryen.bf2042state.model.GiteeRelease
import com.eggtargaryen.bf2042state.model.UpdateInfoViewModel
import com.eggtargaryen.bf2042state.utils.versionCompare
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException


@Composable
fun UpdateButton(
    scaffoldState: ScaffoldState,
    updateInfoViewModel: UpdateInfoViewModel
) {
    val scope = rememberCoroutineScope()

    val updateInfo = updateInfoViewModel.getUpdateInfo()
    val localUriHandler = LocalUriHandler.current
    var hasVersionUpdate by remember {
        mutableStateOf(
            if (updateInfo?.tagName == null) {
                false
            } else {
                versionCompare(BuildConfig.VERSION_NAME, updateInfo.tagName)
            }
        )
    }
    var downloadUrl by remember {
        mutableStateOf(
            if (updateInfo?.tagName == null) {
                ""
            } else {
                updateInfo.assets[0].browserDownloadURL
            }
        )
    }

    SideEffect {
        if (updateInfo?.tagName == null) {
            updateCheck().enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "更新检查失败！"
                            )
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val releaseInfo = response.body?.string()
                        println("update check")
                        try {
                            val moshi = Moshi.Builder()
                                .add(KotlinJsonAdapterFactory())
                                .build()
                            val releaseInfoAdapter = moshi.adapter(GiteeRelease::class.java)
                            val releaseInfoJson = releaseInfoAdapter.fromJson(releaseInfo!!)
                            if (releaseInfoJson != null) {
                                hasVersionUpdate = versionCompare(
                                    BuildConfig.VERSION_NAME,
                                    releaseInfoJson.tagName
                                )
                                downloadUrl = releaseInfoJson.assets[0].browserDownloadURL
                                updateInfoViewModel.postUpdateInfo(releaseInfoJson)
                            } else {
                                throw Exception("更新检查失败！")
                            }
                        } catch (e: Exception) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "更新检查失败！"
                                )
                            }
                        }
                    }
                }
            )
        }
    }

    TextButton(
        onClick = {
            localUriHandler.openUri(downloadUrl)
        },
        enabled = hasVersionUpdate && downloadUrl != "",
    ) {
        Text(
            text = if (hasVersionUpdate) {
                stringResource(id = R.string.login_label_update)
            } else {
                stringResource(id = R.string.login_label_version) + BuildConfig.VERSION_NAME
            },
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary
        )
    }
}
