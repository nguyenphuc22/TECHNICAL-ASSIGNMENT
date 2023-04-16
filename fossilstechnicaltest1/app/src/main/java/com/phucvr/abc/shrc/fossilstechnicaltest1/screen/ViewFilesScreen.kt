package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.Header
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles = viewModel.listData
    val menusLeft = listOf("Essentials","All")
    val menusRight = listOf(TypeSort.TYPE.name,TypeSort.DATE.name,TypeSort.NAME.name,TypeSort.SIZE.name)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Header(menusLeft.first(),menusLeft,menusRight.first(),menusRight)
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
