package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.phucvr.abc.shrc.fossilstechnicaltest1.R

@Composable
fun MyBottomAppBar(modifier: Modifier, isShow : Boolean) {
    if (isShow) {
        BottomAppBar(modifier = modifier, backgroundColor = Color.White) {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_move_files), contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_content_copy), contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_delete_files), contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_info_24), contentDescription = "Localized description")
            }
        }
    }
}