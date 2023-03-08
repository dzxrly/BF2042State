package com.eggtargaryen.bf2042state.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eggtargaryen.bf2042state.R


@Composable
fun BaseDataBadge(
    label: String,
    data: String,
) {
    Column(
        modifier = Modifier.width(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = data,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun BaseDataBadgeRow(
    modifier: Modifier,
    label: String,
    data: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = if (label.isNotEmpty()) "$label:" else "",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = data,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Start
        )
    }
}


@Composable
fun TwoColumnBaseBadge(
    label_left: String,
    data_left: String,
    label_right: String = "",
    data_right: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BaseDataBadgeRow(
            modifier = Modifier.weight(0.5f),
            label = label_left,
            data = data_left
        )
        Spacer(modifier = Modifier.size(2.dp))
        BaseDataBadgeRow(
            modifier = Modifier.weight(0.5f),
            label = label_right,
            data = data_right
        )
    }
}
