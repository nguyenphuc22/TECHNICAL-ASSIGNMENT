package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.phucvr.abc.shrc.fossilstechnicaltest1.R

@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.empty_folder), style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun EmptyScreenPreview() {
    EmptyScreen()
}