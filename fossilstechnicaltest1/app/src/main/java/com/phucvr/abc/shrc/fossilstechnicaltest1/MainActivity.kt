package com.phucvr.abc.shrc.fossilstechnicaltest1

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.phucvr.abc.shrc.fossilstechnicaltest1.model.Folder
import com.phucvr.abc.shrc.fossilstechnicaltest1.repository.LocalDevice.RepositoryLocalDevice
import com.phucvr.abc.shrc.fossilstechnicaltest1.screen.ViewFilesScreen
import com.phucvr.abc.shrc.fossilstechnicaltest1.ui.theme.FossilsTechnicalTest1Theme
import com.phucvr.abc.shrc.fossilstechnicaltest1.viewmodel.MainViewModel
import java.io.File
import java.io.FileFilter
import java.util.Observer

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel = MainViewModel(RepositoryLocalDevice())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FossilsTechnicalTest1Theme {
                // A surface container using the 'background' color from the theme
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

@Composable
fun Greeting(viewModel: MainViewModel) {
    val name by viewModel.name.observeAsState(String)
    Column() {
        Text(text = "Hello $name!")
        Text(text = "Hello")
        Button(onClick = { click(viewModel) }) {

        }

    }
}

fun click(viewModel: MainViewModel) {
    viewModel.setName("Phuc")
    viewModel.getAllData()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FossilsTechnicalTest1Theme {
        Greeting(MainViewModel(RepositoryLocalDevice()))
    }
}