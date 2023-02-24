package com.eggtargaryen.bf2042state.page

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
import com.eggtargaryen.bf2042state.component.RoundOutlineTextField
import com.eggtargaryen.bf2042state.component.loginPageAbout
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun LoginPage(
    onNavToState: () -> Unit = {}
) {
    val platformLabelOptions =
        listOf("PC", "Xbox One", "Xbox Series", "PlayStation 4", "PlayStation 5")
    val platformValOptions = listOf("pc", "xboxone", "xboxseries", "ps4", "ps5")
    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedPlatformText by remember { mutableStateOf("") }
    var selectedPlatform by remember { mutableStateOf(platformValOptions[0]) }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(top = 32.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
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
                Spacer(modifier = Modifier.height(4.dp))
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                ) {
                    RoundOutlineTextField(
                        value = selectedPlatformText,
                        onValueChange = { },
                        label = { Text(stringResource(id = R.string.login_label_platform)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
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
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        },
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.onSecondary)
                    ) {
                        platformLabelOptions.forEachIndexed { platformIndex, selectionOption ->
                            DropdownMenuItem(onClick = {
                                selectedPlatformText = selectionOption
                                selectedPlatform = platformValOptions[platformIndex]
                                expanded = false
                            }) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                RoundOutlineTextField(value = username,
                    onValueChange = { username = it },
                    label = { Text(text = stringResource(id = R.string.login_label_username)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = stringResource(id = R.string.login_label_username),
                            tint = MaterialTheme.colors.primary
                        )
                    })
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onNavToState,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.background
                    )
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
            val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
            BottomDrawer(
                drawerContent = loginPageAbout(),
                drawerState = drawerState,
                scrimColor = MaterialTheme.colors.background,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        onClick = { scope.launch { drawerState.open() } },
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_label_about),
                            color = MaterialTheme.colors.secondary,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
