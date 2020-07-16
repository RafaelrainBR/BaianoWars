package net.winternetwork.bedwars.game

import net.winternetwork.bedwars.api.plugin.ServerPlugin

class Game : ServerPlugin() {

    var timeElapsed = 0

    override fun onEnable() {
        game = this

        initModules()
    }

    override fun onDisable() {
        disableModules()
    }
}