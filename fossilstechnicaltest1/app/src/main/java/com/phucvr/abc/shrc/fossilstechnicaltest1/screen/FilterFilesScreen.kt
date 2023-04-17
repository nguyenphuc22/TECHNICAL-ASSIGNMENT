package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.Header
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.SearchBar
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.TopAppBar
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice.RepositoryLocalDevice
import com.phucvr.abc.shrc.fossilstechnicaltest1.routing.MyRouter
import com.phucvr.abc.shrc.fossilstechnicaltest1.routing.Screen
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.FilterViewModel
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

@Composable
fun FilterFilesScreen(viewModel: FilterViewModel) {
    val listFiles = viewModel.listData
    val menusLeft = viewModel.getMenuLeft()
    val menusRight = viewModel.getMenuRight()
    var alphaLeft = viewModel.isShowLeftMenus.value
    var alphaRight = viewModel.isShowRightMenus.value
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(start = 15.dp, end = 15.dp, top = 15.dp)) {

        SearchBar(text = text,
            onBackClicked = { MyRouter.getInstance().navigateTo(Screen.VIEW_FILES)},
            onTextChange = { text = it },
            onSearchClicked = { viewModel.filterFiles(it) }
        )

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

        ListCard(list = listFiles, listSelected = listFiles,false) {
            if (it.isFolder()) {
                viewModel.onClickFolder(it)
            } else {
                viewModel.onClickFile(it)
            }
        }

        if (viewModel.listData.isEmpty()) {
            EmptyScreen(stringResource(id = R.string.empty_keyword))
            viewModel.hideRightMenus()
            viewModel.hideLeftMenus()
        } else {
            viewModel.showRightMenus()
            viewModel.showLeftMenus()

        }
    }

    BackHandler {
        if (viewModel.isRootFolder()) {
            MyRouter.getInstance().navigateTo(Screen.VIEW_FILES)
        } else {
            viewModel.backStack()
        }
    }
}