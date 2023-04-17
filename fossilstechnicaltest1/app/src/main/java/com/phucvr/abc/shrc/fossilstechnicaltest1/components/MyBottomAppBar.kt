package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings

@Composable
fun MyBottomAppBar(modifier: Modifier, isShow : Boolean, onClickIcons : (String) -> Unit = {}) {
    if (isShow) {
        BottomAppBar(modifier = modifier, backgroundColor = Color.White) {
            IconButton(onClick = { onClickIcons(Settings.OPTION_MOVE) }) {
                Icon(painter = painterResource(id = R.drawable.ic_move_files), contentDescription = "Localized description")
            }
            IconButton(onClick = { onClickIcons(Settings.OPTION_COPY) }) {
                Icon(painter = painterResource(id = R.drawable.ic_content_copy), contentDescription = "Localized description")
            }
            IconButton(onClick = { onClickIcons(Settings.OPTION_DELETE) }) {
                Icon(painter = painterResource(id = R.drawable.ic_delete_files), contentDescription = "Localized description")
            }
            IconButton(onClick = { onClickIcons(Settings.OPTION_INFO) }) {
                Icon(painter = painterResource(id = R.drawable.ic_info_24), contentDescription = "Localized description")
            }
        }
    }
}

@Composable
fun MyBottomAppBarAction(modifier: Modifier, listSelected : List<iFile>, isShow: Boolean, action : String, onClickIcons : (Boolean) -> Unit = {}) {
    BottomAppBar(modifier = modifier, backgroundColor = Color.White) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { }, modifier = Modifier.align(Alignment.CenterStart)) {
                Row() {
                    Icon(painter = painterResource(id = R.drawable.ic_outline_folder_24_black), contentDescription = "Localized description")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(R.string.count,  listSelected.size))
                }
            }

            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                Text(text = stringResource(R.string.cancel), modifier = Modifier.clickable { onClickIcons(false) } , style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = action, modifier = Modifier.clickable { onClickIcons(true) }, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}