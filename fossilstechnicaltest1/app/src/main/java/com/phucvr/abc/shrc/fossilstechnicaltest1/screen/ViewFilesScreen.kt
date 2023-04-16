package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.Header
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.TopAppBar


@SuppressLint("MutableCollectionMutableState")
@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles = viewModel.listData
    val menusLeft = viewModel.getMenuLeft()
    val menusRight = viewModel.getMenuRight()
    var alphaLeft = viewModel.isShowLeftMenus.value
    var alphaRight = viewModel.isShowRightMenus.value
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(start = 15.dp, end = 15.dp, top = 15.dp)) {

        TopAppBar() {
            viewModel.backStack()
        }
        
        Spacer(modifier = Modifier.size(15.dp))

        Header(
            viewModel.getModeList(),
            menusLeft,
            alphaLeft,
            viewModel.getTypeSort().first.name,
            menusRight,
            viewModel.getTypeSort().second,
            alphaRight,
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

        Spacer(modifier = Modifier.size(15.dp))

        ListCard(list = listFiles) {
            if (it.isFolder()) {
                viewModel.onClickFolder(it)
            } else {
                viewModel.onClickFile(it)
            }
        }

        if (viewModel.listData.isEmpty()) {
            EmptyScreen()
            viewModel.hideRightMenus()
        } else {
            viewModel.showRightMenus()
        }
    }

    BackHandler {
        viewModel.backStack()
    }

}
