package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.R

@Composable
fun CardState(dp: Dp,modifier: Modifier) {
    Box(modifier = modifier
        .size(dp)
        .clip(CircleShape)
        .background(Color.Gray))
    {
        Image(painter = painterResource(id = R.drawable.ic_download),
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun CardStatePreview() {
    CardState(10.dp,Modifier)
}