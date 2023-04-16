package com.phucvr.abc.shrc.fossilstechnicaltest1

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.imageLoader
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice.RepositoryLocalDevice
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.PermissionScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.ViewFilesScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.ui.theme.FossilsTechnicalTest1Theme
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilIntent
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilPermission.Companion.isPermissionGranted
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private val viewModel : MainViewModel = MainViewModel(RepositoryLocalDevice())
    @SuppressLint("PermissionLaunchedDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FossilsTechnicalTest1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var permission by remember {
                        mutableStateOf(isPermissionGranted(context = this, Manifest.permission.READ_EXTERNAL_STORAGE))
                    }

                    PermissionScreen(context = this,Manifest.permission.READ_EXTERNAL_STORAGE) { isPermissionGranted ->
                        permission = isPermissionGranted
                        if (isPermissionGranted) {
                            Toast.makeText(this, this.getString(R.string.permission_granted),Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this,this.getString(R.string.permission_denied),Toast.LENGTH_SHORT).show()
                        }
                    }

                    if (permission == true) {
                        viewModel.getAllData()
                        LaunchedEffect(true) {
                            if (viewModel.isModeEssentials()) {
                                viewModel.turnOnModeEssentials()
                            } else {
                                viewModel.turnOffModeEssentials()
                            }
                            viewModel.sort()
                        }
                        ViewFilesScreen(viewModel)
                    }
                }
            }
        }
        initEventCLick()
    }

    fun initEventCLick() {
        clickOpenSpecFile()
    }

    fun clickOpenSpecFile() {
        viewModel.callBackFile {
            UtilIntent.openFile(this,it)
        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.refreshData()
        if (viewModel.isModeEssentials()) {
            viewModel.turnOnModeEssentials()
        } else {
            viewModel.turnOffModeEssentials()
        }
        viewModel.sort()
    }
}

