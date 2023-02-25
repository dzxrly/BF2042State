package com.eggtargaryen.bf2042state.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R


enum class SnackBarType {
    SUCCESS, ERROR, WARNING, INFO
}


@Composable
fun getSnackBarColor(snackBarType: SnackBarType): Color {
    return when (snackBarType) {
        SnackBarType.SUCCESS -> MaterialTheme.colors.primary
        SnackBarType.ERROR -> MaterialTheme.colors.error
        SnackBarType.WARNING -> MaterialTheme.colors.primaryVariant
        SnackBarType.INFO -> MaterialTheme.colors.secondary
        else -> MaterialTheme.colors.secondary
    }
}


@Composable
fun getSnackBarBackgroundColor(snackBarType: SnackBarType): Color {
    return when (snackBarType) {
        SnackBarType.SUCCESS -> MaterialTheme.colors.onPrimary
        SnackBarType.ERROR -> MaterialTheme.colors.onError
        SnackBarType.WARNING -> MaterialTheme.colors.secondaryVariant
        SnackBarType.INFO -> MaterialTheme.colors.onSecondary
        else -> MaterialTheme.colors.onSecondary
    }
}


@Composable
fun getSnackBarIcon(snackBarType: SnackBarType): Painter {
    return when (snackBarType) {
        SnackBarType.SUCCESS -> painterResource(id = R.drawable.round_check_circle_outline_24)
        SnackBarType.ERROR -> painterResource(id = R.drawable.round_error_outline_24)
        SnackBarType.WARNING -> painterResource(id = R.drawable.round_warning_amber_24)
        SnackBarType.INFO -> painterResource(id = R.drawable.round_info_24)
        else -> painterResource(id = R.drawable.round_info_24)
    }
}


@Composable
fun CustomSnackBar(
    snackBarType: SnackBarType,
    message: String,
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = Color.Transparent)
            .padding(32.dp)
            .clip(RoundedCornerShape(50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = getSnackBarBackgroundColor(snackBarType = snackBarType)
                )
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = getSnackBarIcon(snackBarType = snackBarType),
                tint = getSnackBarColor(snackBarType = snackBarType),
                contentDescription = "Icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = message,
                color = getSnackBarColor(snackBarType = snackBarType),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}
