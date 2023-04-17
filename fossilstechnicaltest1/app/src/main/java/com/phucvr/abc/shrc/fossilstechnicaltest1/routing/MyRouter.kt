package com.phucvr.abc.shrc.fossilstechnicaltest1.routing

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat.startActivity
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice.RepositoryLocalDevice
import com.phucvr.abc.shrc.fossilstechnicaltest1.routing.MyRouter.Holder.INSTANCE
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.FilterFilesScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.PermissionScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.ViewFilesScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilPermission
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.FilterViewModel
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel


class MyRouter private constructor() {

    private object Holder { val INSTANCE = MyRouter() }

    companion object {
        @JvmStatic
        fun getInstance(): MyRouter {
            return INSTANCE
        }
    }

    var currentScreen = mutableStateOf(Screen.VIEW_PERMISSION_REQUEST)

    fun navigateTo(destination : Screen) {
        currentScreen.value = destination
    }
}

enum class Screen() {
    VIEW_FILES, FILTER_FILES, VIEW_PERMISSION_REQUEST
}

@Composable
fun ManagerScreen(viewModel: MainViewModel, context: Context, callBackOpenFile: (iFile) -> Unit = {}) {
    when(MyRouter.getInstance().currentScreen.value) {
        Screen.VIEW_FILES -> {
            viewModel.getAllData()
            LaunchedEffect(true) {
                if (viewModel.isModeEssentials()) {
                    viewModel.turnOnModeEssentials()
                } else {
                    viewModel.turnOffModeEssentials()
                }
                viewModel.sort()
            }
            ViewFilesScreen(viewModel,context)

            viewModel.callBackFile {
                callBackOpenFile(it)
            }

        }
        Screen.FILTER_FILES -> {
            val filterViewModel = FilterViewModel(RepositoryLocalDevice())
            FilterFilesScreen(filterViewModel)

            filterViewModel.callBackFile {
                callBackOpenFile(it)
            }

        }
        Screen.VIEW_PERMISSION_REQUEST -> {
            if (UtilPermission.isPermissionGranted(context = context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                LaunchedEffect(key1 = true) {
                    MyRouter.getInstance().navigateTo(Screen.VIEW_FILES)
                }
            } else {
                PermissionScreen(context = context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) { isPermissionGranted ->
                    if (isPermissionGranted) {
                        MyRouter.getInstance().navigateTo(Screen.VIEW_FILES)
                    }
                    if (isPermissionGranted) {
                        Toast.makeText(context, context.getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,context.getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}