package com.jeremydufeux.u_convert.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
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
        ConverterScreen(viewModel)
    }
}