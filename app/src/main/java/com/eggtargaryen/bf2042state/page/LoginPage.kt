package com.eggtargaryen.bf2042state.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eggtargaryen.bf2042state.R
import com.eggtargaryen.bf2042state.component.RoundOutlineTextField


@ExperimentalMaterialApi
@Composable
fun LoginPage() {
    val platformLabelOptions =
        listOf("PC", "Xbox One", "Xbox Series", "PlayStation 4", "PlayStation 5")
    val platformValOptions = listOf("pc", "xboxone", "xboxseries", "ps4", "ps5")

    var username by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedPlatformText by remember { mutableStateOf("") }
    var selectedPlatform by remember { mutableStateOf(platformValOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(32.dp),
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
        RoundOutlineTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = stringResource(id = R.string.login_label_username)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Username Icon",
                    tint = MaterialTheme.colors.primary
                )
            })
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
                        contentDescription = "Game Icon",
                        tint = MaterialTheme.colors.primary
                    )
                },
                readOnly = true,
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                platformLabelOptions.forEachIndexed { platformIndex, selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedPlatformText = selectionOption
                            selectedPlatform = platformValOptions[platformIndex]
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.background
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_manage_search_24),
                contentDescription = "Search Icon",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.login_label_search_btn),
            )
        }
    }
}
