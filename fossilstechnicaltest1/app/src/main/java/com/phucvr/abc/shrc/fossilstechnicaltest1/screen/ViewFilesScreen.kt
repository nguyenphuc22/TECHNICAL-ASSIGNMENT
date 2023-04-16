package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles = viewModel.listData
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        ListCard(list = listFiles) {
            if (it.isFolder()) {
                viewModel.onClickFolder(it)
            } else {
                viewModel.onClickFile(it)
            }
        }
    }

    BackHandler {
        viewModel.onClickBack()
    }

}
