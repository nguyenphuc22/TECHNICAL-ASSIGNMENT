package com.phucvr.abc.shrc.fossilstechnicaltest1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.phucvr.abc.shrc.fossilstechnicaltest1.core.FileManager
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice.RepositoryLocalDevice
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.ViewFilesScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.ui.theme.FossilsTechnicalTest1Theme
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel = MainViewModel(RepositoryLocalDevice())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllData()
        setContent {
            FossilsTechnicalTest1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewFilesScreen(viewModel)
                }
            }
        }
    }
}