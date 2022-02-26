package com.jeremydufeux.u_convert.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jeremydufeux.u_convert.R
import com.jeremydufeux.u_convert.ui.theme.UConvertTheme
import com.jeremydufeux.u_convert.ui.views.ConverterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(viewModel)
        }
    }
}

@Composable
fun App(viewModel: MainActivityViewModel) {
    UConvertTheme {
        Scaffold(
            topBar = {
                AppBar()
            },
        ) { innerPadding ->
            ConverterScreen(
                Modifier.padding(innerPadding),
                viewModel)
        }
    }
}

@Composable
fun AppBar(){
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.primary,)
            )
        },
        backgroundColor = MaterialTheme.colors.secondary
    )
}