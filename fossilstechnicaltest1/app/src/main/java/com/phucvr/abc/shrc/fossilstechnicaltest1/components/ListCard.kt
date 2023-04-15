package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile

@Composable
fun ListCard(list : SnapshotStateList<iFile>, onClickCard: (iFile) -> Unit = {}) {
    LazyColumn {
        this.items(list) { iFile ->
            Card(file = iFile) {
                onClickCard(it)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun ListCardPreview() {
    var a = File("AUDIO",10120000,7,"Nick/Phuc/Phuc","BBHHHJ/asddsa","")
    var b = File("AUDIO",100231221,7,"Nick/Phuc/Phuc","BBHHHJ/asddsa","")
    val arrayList = arrayListOf<iFile>()
    arrayList.add(a)
    arrayList.add(b)
    val mutableList = mutableStateListOf<iFile>()
    mutableList.addAll(arrayList)
    ListCard(mutableList)
}