package com.phucvr.abc.shrc.fossilstechnicaltest1.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

class UtilPermission {
    companion object {
        fun isPermissionGranted(context: Context, MANIFEST_PERMISSION : String): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                MANIFEST_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}