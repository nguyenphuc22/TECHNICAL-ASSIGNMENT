package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.R

@Composable
fun TopAppBar(onClickIconBack: () -> Unit = {},onClickIconSearch: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "", modifier = Modifier.clickable { onClickIconBack() })
        Row(modifier = Modifier.align(Alignment.TopEnd)) {
            Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "", modifier = Modifier.clickable { onClickIconSearch() })
            Spacer(modifier = Modifier.width(15.dp))
            Icon(painter = painterResource(id = R.drawable.ic_more_vert), contentDescription = "")
        }
    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}