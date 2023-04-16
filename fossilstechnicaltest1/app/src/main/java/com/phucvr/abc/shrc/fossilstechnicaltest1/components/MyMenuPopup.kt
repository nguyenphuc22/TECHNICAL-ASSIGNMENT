package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile

@Composable
fun MyMenuPopup(expanded: Boolean, list: List<String> , selected : String ,onTurnOff : (Boolean) -> Unit = {} ,selectedChanged : (String) -> Unit = {}) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onTurnOff(false) }
    ) {
        for (item in list) {
            if (selected.equals(item)) {
                DropdownMenuItem(content = {
                    Text(text = item, color = MaterialTheme.colorScheme.primary)
                }, onClick = {
                    selectedChanged(item)
                    onTurnOff(false)
                })
            } else {
                DropdownMenuItem(content = {
                    Text(text = item)
                }, onClick = {
                    selectedChanged(item)
                    onTurnOff(false)
                })
            }

        }
    }
}

@Preview
@Composable
fun MenuPopupPreview(){
    //MyMenuPopup(expanded)
}
