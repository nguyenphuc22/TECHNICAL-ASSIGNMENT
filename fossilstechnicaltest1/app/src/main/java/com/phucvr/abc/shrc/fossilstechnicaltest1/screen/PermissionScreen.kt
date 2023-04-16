package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilPermission

@Composable
fun PermissionScreen(context: Context, ManiFest_Permission : String, callBack: (isPermissionGranted : Boolean) -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var permissionGranted by remember {
            mutableStateOf(UtilPermission.isPermissionGranted(context,ManiFest_Permission))
        }

        val permissionLauncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { _permissionGranted ->
                // this is called when the user selects allow or deny
                callBack(_permissionGranted)
                permissionGranted = _permissionGranted
            }


        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            enabled = !permissionGranted, // if the permission is NOT granted, enable the button
            onClick = {
                if (!permissionGranted) {
                    // ask for permission
                    permissionLauncher.launch(ManiFest_Permission)
                }
            }) {
            Text(text = if (permissionGranted) "Permission Granted" else "Enable Permission")
        }
    }
}

@Preview
@Composable
fun PermissionScreenPreview() {
    //PermissionScreen()
}