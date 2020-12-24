package net.winternetwork.bedwars.modules.maps

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.modules.maps.command.MapCommands
import net.winternetwork.bedwars.modules.maps.manager.MapManager

class MapModule : Module("Maps") {

    private val config: YamlConfig by lazy {
        YamlConfig(
            game,
            dataFolder,
            "maps.yml"
        )
    }

    override val manager = MapManager(config)

    override fun init() {
        log("Registrando comandos...")
        game.commands(MapCommands(manager))
    }
}