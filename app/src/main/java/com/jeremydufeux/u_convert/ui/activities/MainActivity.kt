package com.jeremydufeux.u_convert.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jeremydufeux.u_convert.ui.theme.UConvertTheme
import com.jeremydufeux.u_convert.ui.views.UConvertAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    UConvertTheme {
        val allScreen = MainActivityScreen.values().toList()
        var currentScreen by rememberSaveable { mutableStateOf(MainActivityScreen.Converter)}

        Scaffold(
            topBar = {
                UConvertAppBar(
                    allScreen = allScreen,
                    onTabSelected = { screen -> currentScreen = screen},
                    currentScreen = currentScreen
                )
            },
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)){
                currentScreen.Content(
                    onScreenChange = { screen ->
                        currentScreen = MainActivityScreen.valueOf(screen)
                    }
                )
            }
        }
    }
}