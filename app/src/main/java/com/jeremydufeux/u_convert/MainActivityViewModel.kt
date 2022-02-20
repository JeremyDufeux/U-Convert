package com.jeremydufeux.u_convert

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jeremydufeux.u_convert.models.Universe
import com.jeremydufeux.u_convert.models.UniverseUi

class MainActivityViewModel : ViewModel() {
    private var universe = Universe()
    var universeUi by mutableStateOf(universe.toUiObject())
    var decimal0TextField by mutableStateOf("0")
    var decimal1TextField by mutableStateOf("1")

    fun setArtnetNet(artnetNet: String){
        universe.setArtnetNet(artnetNet.toInt())
        updateUniverseUi()
    }

    fun setArtnetSubnet(artnetSubnet: String){
        universe.setArtnetSubnet(artnetSubnet.toInt(16))
        updateUniverseUi()
    }

    fun setArtnetUniverse(artnetUniverse: String){
        universe.setArtnetUniverse(artnetUniverse.toInt(16))
        updateUniverseUi()
    }

    fun setDecimalUniverse0(decimalUniverse0: String){
        decimal0TextField = decimalUniverse0
        if (decimalUniverse0.isNotBlank()) {
            universe.setDecimalUniverse0(decimalUniverse0.toInt())
            updateUniverseUi()
        }
    }

    fun setDecimalUniverse1(decimalUniverse1: String){
        decimal1TextField = decimalUniverse1
        if(decimalUniverse1.isNotBlank()) {
            universe.setDecimalUniverse1(decimalUniverse1.toInt())
            updateUniverseUi()
        }
    }

    fun decreaseArtnetNet() {
        universe.setArtnetNet(universe.artnetNet-1)
        updateUniverseUi()
    }
    fun increaseArtnetNet() {
        universe.setArtnetNet(universe.artnetNet+1)
        updateUniverseUi()
    }

    fun decreaseArtnetSubnet() {
        universe.setArtnetSubnet(universe.artnetSubnet-1)
        updateUniverseUi()
    }
    fun increaseArtnetSubnet() {
        universe.setArtnetSubnet(universe.artnetSubnet+1)
        updateUniverseUi()
    }

    fun decreaseArtnetUniverse() {
        universe.setArtnetUniverse(universe.artnetUniverse-1)
        updateUniverseUi()
    }
    fun increaseArtnetUniverse() {
        universe.setArtnetUniverse(universe.artnetUniverse+1)
        updateUniverseUi()
    }

    fun decreaseDecimal() {
        universe.setDecimalUniverse0(universe.decimalUniverse0-1)
        updateUniverseUi()
    }
    fun increaseDecimal() {
        universe.setDecimalUniverse0(universe.decimalUniverse0+1)
        updateUniverseUi()
    }

    fun resetUniverse() {
        universe.resetUniverse()
        updateUniverseUi()
    }

    private fun updateUniverseUi() {
        universeUi = universe.toUiObject()
        decimal0TextField = universeUi.decimalUniverse0.toString()
        decimal1TextField = universeUi.decimalUniverse1.toString()
    }

    private fun Universe.toUiObject(): UniverseUi{
        return UniverseUi(
            decimalUniverse0 = decimalUniverse0,
            decimalUniverse1 = decimalUniverse1,
            artnetNet = artnetNet,
            artnetSubnet = artnetSubnet,
            artnetUniverse = artnetUniverse
        )
    }

    fun onDecimal0FieldDone() {
        if (decimal0TextField.isBlank()){
            decimal0TextField = universeUi.decimalUniverse0.toString()
        }
    }

    fun onDecimal1FieldDone() {
        if (decimal1TextField.isBlank()){
            decimal1TextField = universeUi.decimalUniverse1.toString()
        }
    }
}