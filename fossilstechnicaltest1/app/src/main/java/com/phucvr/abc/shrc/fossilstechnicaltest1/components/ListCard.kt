package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile

@Composable
fun ListCard(list : ArrayList<iFile>) {
    LazyColumn {
        items(list) { ifile ->
            Card(file = ifile)
        }
    }
}

@Preview
@Composable
fun ListCardPreview() {
    var a = File("AUDIO",10120000,7,"Nick/Phuc/Phuc","BBHHHJ/asddsa","")
    var b = File("AUDIO",100231221,7,"Nick/Phuc/Phuc","BBHHHJ/asddsa","")
    val arrayList = arrayListOf<iFile>()
    arrayList.add(a)
    arrayList.add(b)
    ListCard(arrayList)
}