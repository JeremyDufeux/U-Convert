package com.jeremydufeux.u_convert.ui.activities

import androidx.compose.runtime.Composable
import com.jeremydufeux.u_convert.ui.views.ConverterScreen
import com.jeremydufeux.u_convert.ui.views.SavedUniversesScreen

enum class MainActivityScreen(
    val title: String,
    val body: @Composable ((String) -> Unit) -> Unit
){
    Converter(
        title = "Converter",
        body = { ConverterScreen() },
    ),
    SavedUniverses(
        title = "Saved Universes",
        body = { SavedUniversesScreen() }
    );

    @Composable
    fun Content(onScreenChange: (String) -> Unit){
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): MainActivityScreen =
            when (route?.substringBefore("/")){
                Converter.name -> Converter
                SavedUniverses.name -> SavedUniverses
                null -> Converter
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}