package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile

@Composable
fun ListCard(list : SnapshotStateList<iFile>,listSelected : SnapshotStateList<iFile>, actionStateList : Boolean, onClickCard: (iFile) -> Unit = {}) {

    LazyColumn {
        this.itemsIndexed(list) { index , iFile ->
            Card(file = iFile,actionStateList, isSelected = listSelected, onClickCard = {onClickCard(it)})
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
    //ListCard(mutableList,true)
}