package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import android.annotation.SuppressLint
import android.provider.MediaStore
import android.widget.VideoView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.Header
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@SuppressLint("MutableCollectionMutableState")
@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles = viewModel.listData
    val menusLeft = viewModel.getMenuLeft()
    val menusRight = viewModel.getMenuRight()
    var alpha = viewModel.isShowLeftMenus.value
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(start = 15.dp, end = 15.dp, top = 15.dp)) {

        Header(
            viewModel.getModeList(),
            menusLeft,
            alpha,
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

        if (viewModel.listData.isEmpty()) {
            EmptyScreen()
        }
    }

    BackHandler {
        viewModel.onClickBack()
        if (viewModel.isRootFolder()) {
            if (viewModel.isModeEssentials()) {
                viewModel.turnOnModeEssentials()
            } else {
                viewModel.turnOffModeEssentials()
            }
            viewModel.sort()
        }
    }

}
