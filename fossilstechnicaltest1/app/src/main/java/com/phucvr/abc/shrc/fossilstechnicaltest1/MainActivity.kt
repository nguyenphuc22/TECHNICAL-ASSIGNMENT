package com.phucvr.abc.shrc.fossilstechnicaltest1

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice.RepositoryLocalDevice
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.PermissionScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.ViewFilesScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.ui.theme.FossilsTechnicalTest1Theme
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilPermission.Companion.isPermissionGranted
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {

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
                            Toast.makeText(this,"Permission has been granted",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this,"Permission has been denied",Toast.LENGTH_SHORT).show()
                        }
                    }

                    if (permission == true) {
                        viewModel.getAllData()
                        ViewFilesScreen(viewModel)
                    }
                }
            }
        }
        initEventCLick()
    }

    fun initEventCLick() {
        viewModel.callBackFile {
            val uri: Uri = Uri.parse(it.getPath())
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "*/*")
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("NickSeven","Phuc")
    }
    // check initially if the permission is granted
}

