package net.winternetwork.bedwars.game

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.plugin.ServerPlugin

class Game : ServerPlugin() {
    var timeElapsed = 0

    val mapsConfig: YamlConfig by lazy {
        YamlConfig(
                this,
                dataFolder,
                "maps.yml"
        )
    }

    val generatorsConfig: YamlConfig by lazy {
        YamlConfig(
                this,
                dataFolder,
                "generators.yml"
        )
    }

    override fun onEnable() {
        game = this
        mapsConfig; generatorsConfig

        initModules()
    }

    override fun onDisable() {
        disableModules()
    }
}