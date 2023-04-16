package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.TypeSort

@Composable
fun Header(selectedLeft : String, listMenuItemLeft : List<String>,selectedRight : String, listMenuItemRight : List<String>) {
    var expandedLeft by remember { mutableStateOf(false) }
    var expandedRight by remember { mutableStateOf(false) }
    var selectedModeLeft by remember { mutableStateOf(selectedLeft) }
    var selectedModeRight by remember { mutableStateOf(selectedRight) }
    var stateIcon by remember { mutableStateOf(false) }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Row(modifier = Modifier
            .align(Alignment.TopStart)
            .wrapContentSize(Alignment.TopStart)
            .clickable { expandedLeft = true }) {
            Text(text = selectedModeLeft, style = MaterialTheme.typography.titleMedium)
            MyMenuPopup(expandedLeft, listMenuItemLeft,selectedModeLeft, selectedChanged = {selectedModeLeft = it}, onTurnOff = {expandedLeft = it })
            Spacer(modifier = Modifier.width(5.dp))
            Icon(painter = painterResource(id = R.drawable.ic_arrow_drop_down), contentDescription = "")
        }
        Row(
            Modifier
                .align(Alignment.TopEnd)
                .wrapContentSize(Alignment.TopStart)) {
            Text(text = "Name", style = MaterialTheme.typography.titleMedium, modifier = Modifier.clickable { expandedRight = true } )
            MyMenuPopup(expandedRight, listMenuItemRight,selectedModeRight, selectedChanged = {selectedModeRight = it}, onTurnOff = {expandedRight = it })
            Text(text = " | ", style = MaterialTheme.typography.titleMedium)

            if (stateIcon) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "", modifier = Modifier.clickable { stateIcon = !stateIcon })
            } else {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_up), contentDescription = "", modifier = Modifier.clickable { stateIcon = !stateIcon })
            }
        }
    }

}

@Preview
@Composable
fun HeaderPreview() {
    Header(selectedLeft = "Essentials", listMenuItemLeft = listOf("Essentials","All"), selectedRight = TypeSort.TYPE.name, listMenuItemRight = listOf(TypeSort.TYPE.name,TypeSort.NAME.name,TypeSort.SIZE.name,TypeSort.DATE.name))
}