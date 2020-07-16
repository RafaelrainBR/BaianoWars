package net.winternetwork.bedwars.game.modules.maps

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.maps.command.MapCommands
import net.winternetwork.bedwars.game.modules.maps.manager.MapManager

class MapModule : Module("Maps") {

    val mapManager = MapManager()

    private val config: YamlConfig by lazy {
        YamlConfig(
                game,
                dataFolder,
                "maps.yml"
        )
    }

    override fun init() {
        log("Carregando mapas...")
        mapManager.loadAll(config)

        log("Registrando comandos...")
        game.commands(MapCommands(mapManager))
    }

    override fun disable() {
        log("Salvando mapas...")
        mapManager.unloadAll(config)
    }
}