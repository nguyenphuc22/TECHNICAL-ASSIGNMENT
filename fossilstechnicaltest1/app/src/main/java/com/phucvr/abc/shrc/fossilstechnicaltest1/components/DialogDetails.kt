package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.UtilConvert

@Composable
fun DialogDetails(list : SnapshotStateList<iFile>, onDismissRequest: () -> Unit = {}) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Surface(shape = RoundedCornerShape(16.dp), color = Color.White) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {
                Text(text = stringResource(id = R.string.details) , style = MaterialTheme.typography.titleSmall)
                Row() {
                    Icon(painter = painterResource(id = R.drawable.ic_folder)  , contentDescription = "")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text =  stringResource(R.string.count, list.size))
                }
                var size : Long = 0

                for (item in list) {
                    size += item.getSize()
                }
                Text(text = stringResource(id = R.string.totalSize), style = MaterialTheme.typography.labelSmall, color = Color.Black.copy(0.3f))
                Text(text = UtilConvert.convertLongToSize1000(size))

                var count : Int = 0

                for (item in list) {
                    count += item.getCount()
                }
                Text(text = stringResource(id = R.string.contains), style = MaterialTheme.typography.labelSmall, color = Color.Black.copy(0.3f))
                Text(text = stringResource(R.string.count, count))

            }
        }

    }
}

@Preview
@Composable
fun DialogDetailsPreview() {
    //DialogDetails()
}