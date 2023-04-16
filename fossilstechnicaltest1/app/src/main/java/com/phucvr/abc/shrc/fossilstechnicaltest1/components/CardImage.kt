package com.phucvr.abc.shrc.fossilstechnicaltest1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.phucvr.abc.shrc.fossilstechnicaltest1.R
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.iFile
import com.phucvr.abc.shrc.fossilstechnicaltest1.util.Settings

@Composable
fun CardImage(iFile: iFile) {
    Box(modifier = Modifier.padding(10.dp)
    )
    {
        if (iFile.isFolder()) {
            Image(painter = painterResource(id = R.drawable.ic_outline_folder_24) ,
                contentDescription = "",
                Modifier
                    .size(30.dp)
                    .align(Alignment.Center))
        } else {
            if (iFile.getExtension().equals("mp4")) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_music_video_24) ,
                    contentDescription = "",
                    Modifier
                        .size(30.dp)
                        .align(Alignment.Center))
            } else {
                Image(painter = rememberAsyncImagePainter(model = iFile.getPath()) ,
                    contentDescription = "",
                    Modifier.align(Alignment.Center).size(30.dp),
                    contentScale = ContentScale.Fit)
            }
        }
        

        val drawable = Settings.STATE_ICON_OF_CARD.get(iFile.getName())

        if (drawable != null) {
            CardState(17.dp,Modifier.align(Alignment.BottomEnd),drawable)
        }
    }
}

@Preview
@Composable
fun CardImagePreview() {
    //CardImage()
}