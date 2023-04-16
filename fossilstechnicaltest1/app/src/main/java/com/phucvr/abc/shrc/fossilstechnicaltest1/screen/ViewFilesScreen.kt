package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.Header
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles by remember { mutableStateOf(viewModel.listData) }
    val menusLeft = viewModel.getMenuLeft()
    val menusRight = viewModel.getMenuRight()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        Header(
            viewModel.getModeList(),
            menusLeft,
            viewModel.getTypeSort().first.name,
            menusRight,
            viewModel.getTypeSort().second,
            onClickMenuLeft = {
                viewModel.setModeList(it)
                if (it.equals(menusLeft.first())) {
                    viewModel.turnOnModeEssentials()
                } else {
                    viewModel.turnOffModeEssentials()
                }
            },
            onClickMenuRight = {
                viewModel.sort(TypeSort.valueOf(it.first),it.second)
            }
        )

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
