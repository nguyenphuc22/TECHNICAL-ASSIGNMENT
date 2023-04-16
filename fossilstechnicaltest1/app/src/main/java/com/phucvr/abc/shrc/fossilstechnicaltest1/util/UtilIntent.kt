package com.phucvr.abc.shrc.fossilstechnicaltest1.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile

class UtilIntent {
    companion object {
        fun openFile(context: Context,iFile: iFile) {
            val uri: Uri = Uri.parse(iFile.getPath())
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "*/*")
            context.startActivity(intent)
        }
    }
}