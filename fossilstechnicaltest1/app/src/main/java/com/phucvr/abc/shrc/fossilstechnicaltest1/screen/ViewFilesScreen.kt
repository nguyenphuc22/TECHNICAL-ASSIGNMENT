package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.Header
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.MyBottomAppBar
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.TopAppBar
import com.phucvr.abc.shrc.fossilstechnicaltest1.routing.MyRouter
import com.phucvr.abc.shrc.fossilstechnicaltest1.routing.Screen
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings


@SuppressLint("MutableCollectionMutableState")
@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles = viewModel.listData
    val menusLeft = viewModel.getMenuLeft()
    val menusRight = viewModel.getMenuRight()
    var alphaLeft = viewModel.isShowLeftMenus.value
    var alphaRight = viewModel.isShowRightMenus.value
    var actionState by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.TopStart)
            .background(Color.White)
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)) {

            TopAppBar(
                listMenu = Settings.LIST_MENU_OPTION,

                hideLeftMenus = actionState,

                onClickIconBack =  {
                    if (actionState) {
                        actionState = false
                    } else {
                        viewModel.backStack()
                    }
                },

                onClickIconSearch = {
                    MyRouter.getInstance().navigateTo(Screen.FILTER_FILES)
                },

                onClickOption = {
                    when(it) {
                        Settings.OPTION_EDIT -> {
                            actionState = true
                        }
                    }
                }

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

            ListCard(list = listFiles,actionState) {
                if (!actionState) {
                    if (it.isFolder()) {
                        viewModel.onClickFolder(it)
                    } else {
                        viewModel.onClickFile(it)
                    }
                }

            }

            if (viewModel.listData.isEmpty()) {
                EmptyScreen()
                viewModel.hideRightMenus()
            } else {
                viewModel.showRightMenus()
            }
        }

        MyBottomAppBar(modifier = Modifier.fillMaxWidth().align(Alignment.BottomStart), isShow = actionState)
    }


    BackHandler {
        if (actionState) {
            actionState = false
        } else {
            viewModel.backStack()
        }
    }

}
