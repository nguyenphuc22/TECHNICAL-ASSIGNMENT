package com.phucvr.abc.shrc.fossilstechnicaltest1.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.phucvr.abc.shrc.fossilstechnicaltest1.components.ListCard
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

@Composable
fun ViewFilesScreen(viewModel: MainViewModel) {
    val listFiles = viewModel._listData
    Column {
        ListCard(list = listFiles)
    }

}
