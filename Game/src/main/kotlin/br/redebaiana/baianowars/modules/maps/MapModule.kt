package br.redebaiana.baianowars.modules.maps

import br.redebaiana.baianowars.api.config.YamlConfig
import br.redebaiana.baianowars.api.module.Module
import br.redebaiana.baianowars.game.game
import br.redebaiana.baianowars.modules.maps.command.MapCommands
import br.redebaiana.baianowars.modules.maps.manager.MapManager

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