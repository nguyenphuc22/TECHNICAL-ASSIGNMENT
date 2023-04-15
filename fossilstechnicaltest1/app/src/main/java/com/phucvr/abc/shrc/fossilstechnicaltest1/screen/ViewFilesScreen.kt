package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {

    val listFiles : ArrayList<iFile> by viewModel.listData.observeAsState(arrayListOf<iFile>())

    Column {
        ListCard(list = listFiles)
    }

}
