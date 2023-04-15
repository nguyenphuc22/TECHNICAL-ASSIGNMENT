package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles = viewModel.listData
    Column {
        ListCard(list = listFiles) {
            if (it.isFolder()) {
                viewModel.onClickFolder(it)
            } else {
                viewModel.onClickFile(it)
            }
        }
    }

}
