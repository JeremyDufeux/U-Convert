package com.jeremydufeux.u_convert.views

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeremydufeux.u_convert.MainActivityViewModel
import com.jeremydufeux.u_convert.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConverterScreen(viewModel: MainActivityViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    fun closeKeyBoard(){
        keyboardController?.hide()
        focusManager.clearFocus()
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    closeKeyBoard()
                })
            }
    ) {
        ConstraintLayout {
            val (column, buttons) = createRefs()

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
                    .constrainAs(column) {
                        top.linkTo(parent.top)
                        bottom.linkTo(buttons.top)
                        height = Dimension.fillToConstraints
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(8.dp))

                ArtnetPart(viewModel) { closeKeyBoard() }

                Divider(
                    color = MaterialTheme.colors.primary,
                    thickness = 2.dp,
                    modifier = Modifier.padding(20.dp)
                )

                DecimalPart(viewModel) { closeKeyBoard() }
            }

            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .constrainAs(buttons) {
                        bottom.linkTo(parent.bottom, margin = 8.dp)
                    },
            ) {
                Button(
                    onClick = {
                        viewModel.resetUniverse()
                        closeKeyBoard()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Reset",
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.W400)
                    )
                }

                Button(
                    onClick = {
                        closeKeyBoard()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Save Universe",
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.W400)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtnetPart(
    viewModel: MainActivityViewModel,
    closeKeyBoard: () -> Unit
){
    Text(
        text = "Artnet",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.h4,
    )
    Row {
        ArtnetElement(
            name = "Net",
            list = getNetList(),
            value = viewModel.universeUi.artnetNet.toString(),
            onValueChange = {
                viewModel.setArtnetNet(it)
                closeKeyBoard()
            },
            onDecrease = {
                viewModel.decreaseArtnetNet()
                closeKeyBoard()
            },
            onIncrease = {
                viewModel.increaseArtnetNet()
                closeKeyBoard()
            }
        )

        ArtnetElement(
            name = "Subnet",
            list = getHexList(),
            value = Integer.toHexString(viewModel.universeUi.artnetSubnet).uppercase(),
            onValueChange = {
                viewModel.setArtnetSubnet(it)
                closeKeyBoard()
            },
            onDecrease = {
                viewModel.decreaseArtnetSubnet()
                closeKeyBoard()
            },
            onIncrease = {
                viewModel.increaseArtnetSubnet()
                closeKeyBoard()
            }
        )

        ArtnetElement(
            name = "Universe",
            list = getHexList(),
            value = Integer.toHexString(viewModel.universeUi.artnetUniverse).uppercase(),
            onValueChange = {
                viewModel.setArtnetUniverse(it)
                closeKeyBoard()
            },
            onDecrease = {
                viewModel.decreaseArtnetUniverse()
                closeKeyBoard()
            },
            onIncrease = {
                viewModel.increaseArtnetUniverse()
                closeKeyBoard()
            }
        )
    }
}

@Composable
fun RowScope.ArtnetElement(
    name: String,
    list: List<String>,
    value: String,
    onValueChange: (String) -> Unit,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
){
    Column(modifier = Modifier
        .weight(1f)
        .padding(5.dp)) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h6,
        )

        DropDownMenu(
            value = value,
            items = list,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            TextButton(onClick = onDecrease)
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = "Artnet $name minus",
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                    tint = MaterialTheme.colors.primary
                )
            }

            TextButton(onClick = onIncrease) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Artnet $name plus",
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
fun DecimalPart(
    viewModel: MainActivityViewModel,
    closeKeyBoard: () -> Unit
){
    Text(
        text = "Decimal",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.h4,
    )
    Row {
        DecimalElement(
            name = "Beginning at 0",
            value = viewModel.decimal0TextField,
            onValueChange = viewModel::setDecimalUniverse0,
            onKeyboardDone = {
                viewModel.onDecimal0FieldDone()
                closeKeyBoard()
            }
        )
        TextButton(modifier = Modifier
            .weight(1f)
            .padding(0.dp, 30.dp, 0.dp, 0.dp),
            onClick = {
                viewModel.decreaseDecimal()
                closeKeyBoard()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "Decimal minus",
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                tint = MaterialTheme.colors.primary
            )
        }
        TextButton(modifier = Modifier
            .weight(1f)
            .padding(0.dp, 30.dp, 0.dp, 0.dp),
            onClick = {
                viewModel.increaseDecimal()
                closeKeyBoard()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "Decimal plus",
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                tint = MaterialTheme.colors.primary
            )
        }
        DecimalElement(
            name = "Beginning at 1",
            value = viewModel.decimal1TextField,
            onValueChange = viewModel::setDecimalUniverse1,
            onKeyboardDone = {
                viewModel.onDecimal1FieldDone()
                closeKeyBoard()
            }
        )
    }
}

@Composable
fun RowScope.DecimalElement(
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    onKeyboardDone: () -> Unit,
){
    Column(
        modifier = Modifier
            .weight(2f)
            .padding(5.dp)
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onKeyboardDone()
                }
            )
        )
    }
}


fun getNetList(): List<String> {
    val list = mutableListOf<String>()
    for(i in 0..127){
        list.add(i.toString())
    }
    return list
}

fun getHexList(): List<String> = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")