package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.File
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilTime

@Composable
fun Card(file: iFile, onClickCard: (iFile) -> Unit = {}) {
    Row(modifier = Modifier
        .background(Color.White)
        .padding(8.dp)
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clickable { onClickCard(file) }) {

        CardImage(file)

        Column(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 12.dp)) {

            Text(text = file.getName(),
                maxLines = 1,
                color = Color.Black)

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ) {

                Text(text = UtilTime.convertLongToTime(file.getLastModified()),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.align(alignment = Alignment.TopStart))

                Text(text = stringResource(R.string.count,  file.getCount()),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.align(alignment = Alignment.TopEnd))

                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = .2f),modifier = Modifier.align(Alignment.BottomEnd))


            }




        }

    }
}

@Preview
@Composable
private fun CardReview() {
    Card(File("AUDIO",1000,7,"Nick/Phuc/Phuc","BBHHHJ/asddsa",""))
}