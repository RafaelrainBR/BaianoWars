package net.winternetwork.bedwars.game.modules.maps.command

import me.saiintbrisson.commands.Execution
import me.saiintbrisson.commands.annotations.Command
import net.winternetwork.bedwars.game.modules.maps.manager.MapManager
import net.winternetwork.bedwars.game.modules.maps.model.GameMap

class MapCommands(
        val mapManager: MapManager
) {

    @Command(
            name = "mapsetup",
            permission = "bedwars.command.mapsetup"
    )
    fun command(execution: Execution) {
        execution.sendMessage(arrayOf(
                "",
                "§e§l/mapsetup §fcreate §e<nome> §6§l- Cria um mapa",
                "§e§l/mapsetup §flocation §e<nome> <id> §6§l- Seta location",
                ""
        ))
    }

    @Command(
            name = "mapsetup.create",
            permission = "bedwars.command.mapsetup"
    )
    fun create(execution: Execution, name: String, maxPlayers: Int) {
        GameMap(
                name,
                maxPlayers,
                execution.player.location
        ).also {
            execution.sendMessage(it.toString())
            mapManager.add(it)
        }
    }
}