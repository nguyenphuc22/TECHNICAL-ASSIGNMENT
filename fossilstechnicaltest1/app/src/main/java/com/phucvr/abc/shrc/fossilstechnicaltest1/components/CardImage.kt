package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.R

@Composable
fun CardImage() {
    Box(modifier = Modifier.padding(10.dp)
    )
    {
        Image(painter = painterResource(id = R.drawable.ic_outline_folder_24) , contentDescription = "",
            Modifier
                .size(30.dp)
                .align(Alignment.Center))
        CardState(15.dp,Modifier.align(Alignment.BottomEnd))
    }
}

@Preview
@Composable
fun CardImagePreview() {
    CardImage()
}