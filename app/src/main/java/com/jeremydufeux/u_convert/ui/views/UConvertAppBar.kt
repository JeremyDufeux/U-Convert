package com.jeremydufeux.u_convert.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jeremydufeux.u_convert.R
import com.jeremydufeux.u_convert.ui.activities.MainActivityScreen

@Composable
fun UConvertAppBar(
    allScreen: List<MainActivityScreen>,
    onTabSelected: (MainActivityScreen) -> Unit,
    currentScreen: MainActivityScreen,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSecondary,)
                )
            },
            backgroundColor = MaterialTheme.colors.secondary
        )

        val currentIndex = allScreen.indexOf(currentScreen)
        TabRow(
            selectedTabIndex = currentIndex,
            backgroundColor = MaterialTheme.colors.background,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[currentIndex])
                        .height(2.dp)
                        .border(1.dp, MaterialTheme.colors.primary)
                )
            },
        ) {
            allScreen.forEachIndexed { index, screen ->
                Tab(
                    selected = currentIndex == index,
                    onClick = {
                        onTabSelected(screen)
                    },
                    text = {
                        Text(text = screen.title)
                    },
                )
            }
        }
    }
}