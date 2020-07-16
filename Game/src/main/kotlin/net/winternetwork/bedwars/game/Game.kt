package net.winternetwork.bedwars.game

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.winternetwork.bedwars.api.plugin.ServerPlugin
import net.winternetwork.bedwars.game.scheduler.GameScheduler
import net.winternetwork.bedwars.game.settings.GameSettings

class Game : ServerPlugin() {

    var timeElapsed = 0

    override fun onEnable() {
        GlobalScope.launch {
            game = this@Game
            GameSettings.canStart = config.getBoolean("canStart")

            initModules()
            GameScheduler()
        }
    }

    override fun onDisable() {
        disableModules()
    }
}