package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.*
import com.phucvr.abc.shrc.fossilstechnicaltest1.routing.MyRouter
import com.phucvr.abc.shrc.fossilstechnicaltest1.routing.Screen
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings


@SuppressLint("MutableCollectionMutableState", "UnrememberedMutableState")
@Composable
fun ViewFilesScreen(viewModel: MainViewModel,context : Context) {
    val listFiles = viewModel.listData
    val menusLeft = viewModel.getMenuLeft()
    val menusRight = viewModel.getMenuRight()
    var alphaLeft = viewModel.isShowLeftMenus.value
    var alphaRight = viewModel.isShowRightMenus.value
    var actionState by remember { mutableStateOf(false) }
    var listSelected = viewModel.listSelected
    var actionSelectPath by remember { mutableStateOf(false) }
    var actionName by remember { mutableStateOf(Settings.OPTION_MOVE) }
    var isShowDialog by remember { mutableStateOf(false) }

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
                            actionSelectPath = false
                            listSelected.clear()
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

            ListCard(list = listFiles,listSelected = listSelected,actionState) {
                if (actionState) {
                    if (listSelected.contains(it)) {
                        listSelected.remove(it)
                    } else {
                        listSelected.add(it)
                    }
                } else {
                    if (it.isFolder()) {
                        viewModel.onClickFolder(it)
                    } else {
                        viewModel.onClickFile(it)
                    }
                }

            }

            if (viewModel.listData.isEmpty()) {
                EmptyScreen(stringResource(id = R.string.empty_folder))
                viewModel.hideRightMenus()
            } else {
                viewModel.showRightMenus()
            }
        }

        MyBottomAppBar(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomStart), isShow = actionState ,
            onClickIcons =  {
                actionName = it
                when(it) {
                    Settings.OPTION_MOVE, Settings.OPTION_COPY, Settings.OPTION_DELETE -> {
                        actionState = false
                        actionSelectPath = true
                    }
                    Settings.OPTION_INFO -> {
                        isShowDialog = true
                    }
                }
            }
        )

        if (isShowDialog) {
            DialogDetails(listSelected) {
                isShowDialog = false
            }
        }

        if (actionSelectPath) {
            MyBottomAppBarAction(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart), listSelected = listSelected, isShow = true, action = actionName,
                    onClickIcons = {
                        actionSelectPath = false
                        if (it) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                if (!Environment.isExternalStorageManager()) {
                                    val intent = Intent(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                                    ContextCompat.startActivity(context, intent, null)
                                } else {
                                    when(actionName) {
                                        Settings.OPTION_MOVE -> {
                                            viewModel.moveFiles(listSelected)
                                            viewModel.refreshData()
                                            if (viewModel.isModeEssentials()) {
                                                viewModel.turnOnModeEssentials()
                                            } else {
                                                viewModel.turnOffModeEssentials()
                                            }
                                            viewModel.sort()
                                        }
                                        Settings.OPTION_DELETE -> {
                                            viewModel.deleteFile(listSelected)
                                            viewModel.refreshData()
                                            if (viewModel.isModeEssentials()) {
                                                viewModel.turnOnModeEssentials()
                                            } else {
                                                viewModel.turnOffModeEssentials()
                                            }
                                            viewModel.sort()
                                        }
                                        Settings.OPTION_COPY -> {
                                            viewModel.copyFiles(listSelected)
                                            viewModel.refreshData()
                                            if (viewModel.isModeEssentials()) {
                                                viewModel.turnOnModeEssentials()
                                            } else {
                                                viewModel.turnOffModeEssentials()
                                            }
                                            viewModel.sort()
                                        }
                                    }

                                }
                            }
                        }
                    }
                )
        }
    }


    BackHandler {
        if (actionState) {
            actionState = false
        } else {
            viewModel.backStack()
        }
    }

}
