package com.jeremydufeux.u_convert.models

private const val MAX_UNIVERSES = 32768
private const val MAX_ARTNET_NET = 128
private const val MAX_ARTNET_SUBNET = 16
private const val MAX_ARTNET_UNIVERSE = 16

class Universe(
    private var absoluteUniverse: Int = 0
) {
    var decimalUniverse0: Int = absoluteUniverse
        private set
    var decimalUniverse1: Int = decimalUniverse0+1
        private set
    var artnetNet: Int = 0
        private set
    var artnetSubnet: Int = 0
        private set
    var artnetUniverse: Int = 0
        private set

    init {
        updateUniverse()
    }

    private fun updateUniverse() {
        decimalUniverse0 = absoluteUniverse
        decimalUniverse1 = absoluteUniverse+1
        artnetNet = absoluteUniverse / 0x100
        artnetSubnet = (absoluteUniverse / 0x10) % 16
        artnetUniverse = absoluteUniverse % 16
    }

    fun setArtnetNet(newArtnetNet: Int){
        when {
            newArtnetNet in 0 until MAX_ARTNET_NET -> {
                artnetNet = newArtnetNet
                absoluteUniverse =
                    artnetNet * MAX_ARTNET_UNIVERSE * MAX_ARTNET_SUBNET + artnetSubnet * MAX_ARTNET_UNIVERSE + artnetUniverse
                updateUniverse()
            }
            newArtnetNet < 0 -> {
                artnetNet = 0
                updateUniverse()
            }
            artnetNet > MAX_ARTNET_NET -> {
                artnetSubnet = MAX_ARTNET_NET
                updateUniverse()
            }
        }
    }

    fun setArtnetSubnet(newArtnetSubnet: Int){
        when {
            newArtnetSubnet in 0 until MAX_ARTNET_SUBNET -> {
                artnetSubnet = newArtnetSubnet
                absoluteUniverse =
                    artnetNet * MAX_ARTNET_UNIVERSE * MAX_ARTNET_SUBNET + artnetSubnet * MAX_ARTNET_UNIVERSE + artnetUniverse
                updateUniverse()
            }
            newArtnetSubnet < 0 -> {
                artnetSubnet = 0
                updateUniverse()
            }
            newArtnetSubnet > MAX_ARTNET_SUBNET -> {
                artnetSubnet = MAX_ARTNET_SUBNET
                updateUniverse()
            }
        }
    }

    fun setArtnetUniverse(newArtnetUniverse: Int){
        when{
            newArtnetUniverse in 0 until MAX_ARTNET_UNIVERSE -> {
                artnetUniverse = newArtnetUniverse
                absoluteUniverse = artnetNet * MAX_ARTNET_UNIVERSE * MAX_ARTNET_SUBNET + artnetSubnet * MAX_ARTNET_UNIVERSE + artnetUniverse
                updateUniverse()
            }
            newArtnetUniverse < 0 -> {
                artnetUniverse = 0
                updateUniverse()
            }
            newArtnetUniverse > MAX_ARTNET_UNIVERSE -> {
                artnetUniverse = MAX_ARTNET_UNIVERSE
                updateUniverse()
            }
        }
    }

    fun resetUniverse() {
        absoluteUniverse = 0
        updateUniverse()
    }

    fun setDecimalUniverse0(newDecimalUniverse0: Int) {
        when {
            newDecimalUniverse0 in 0 until MAX_UNIVERSES -> {
                absoluteUniverse = newDecimalUniverse0
                updateUniverse()
            }
            newDecimalUniverse0 < 0 -> {
                absoluteUniverse = 0
                updateUniverse()
            }
            newDecimalUniverse0 > MAX_UNIVERSES -> {
                absoluteUniverse = MAX_UNIVERSES
                updateUniverse()
            }
        }
    }

    fun setDecimalUniverse1(newDecimalUniverse1: Int) {
        when {
            newDecimalUniverse1 in 0 until MAX_UNIVERSES+1 -> {
                absoluteUniverse = newDecimalUniverse1 - 1
                updateUniverse()
            }
            newDecimalUniverse1 < 1 -> {
                absoluteUniverse = 0
                updateUniverse()
            }
            newDecimalUniverse1 > MAX_UNIVERSES-1 -> {
                absoluteUniverse = MAX_UNIVERSES
                updateUniverse()
            }
        }
    }
}
