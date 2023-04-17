package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilConvert

@Composable
fun Card(file: iFile, actionSate : Boolean, onClickCard: (iFile) -> Unit = {}) {
    var stateButton by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .background(Color.White)
        .padding(top = 8.dp, bottom = 8.dp)
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clickable {
            onClickCard(file)
            stateButton = !stateButton
        }) {

        if (actionSate) {


            RadioButton(selected = stateButton, onClick = { stateButton = !stateButton }, colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary))

            Spacer(modifier = Modifier.width(10.dp))
        }

        CardImage(file)

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight())
        {

            Text(text = file.getName(),
                maxLines = 1,
                color = Color.Black)

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ) {

                Text(text = UtilConvert.convertLongToTime(file.getLastModified()),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.align(alignment = Alignment.TopStart))

                if (file.isFolder()) {
                    Text(text = stringResource(R.string.count,  file.getCount()),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.align(alignment = Alignment.TopEnd))
                } else {
                    Text(text = UtilConvert.convertLongToSize1000(file.getSize()),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.align(alignment = Alignment.TopEnd))
                }


                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = .2f),modifier = Modifier.align(Alignment.BottomEnd))


            }




        }




    }
}

@Preview
@Composable
private fun CardReview() {
    Card(File("AUDIO",1000,7,"Nick/Phuc/Phuc","BBHHHJ/asddsa",""),true)
}