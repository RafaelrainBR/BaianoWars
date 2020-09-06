package net.winternetwork.bedwars.game

import kotlinx.coroutines.runBlocking
import net.winternetwork.bedwars.api.plugin.ServerPlugin
import net.winternetwork.bedwars.game.scheduler.GameScheduler
import net.winternetwork.bedwars.game.settings.GameSettings

class Game : ServerPlugin() {

    var timeElapsed = 0

    override fun onEnable() {
        game = this@Game

        saveDefaultConfig()
        GameSettings.canStart = config.getBoolean("canStart")

        runBlocking {
            initModules()
            GameScheduler()
        }
    }

    override fun onDisable() {
        disableModules()
    }
}