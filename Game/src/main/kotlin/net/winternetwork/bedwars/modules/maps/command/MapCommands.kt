package net.winternetwork.bedwars.modules.maps.command

import me.saiintbrisson.minecraft.command.annotation.Command
import me.saiintbrisson.minecraft.command.command.Context
import me.saiintbrisson.minecraft.command.target.CommandTarget
import net.winternetwork.bedwars.modules.maps.manager.MapManager
import net.winternetwork.bedwars.modules.maps.model.GameMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MapCommands(
    private val mapManager: MapManager
) {

    @Command(
        name = "mapsetup",
        permission = "bedwars.command.mapsetup",
        target = CommandTarget.PLAYER
    )
    fun command(ctx: Context<CommandSender>) {
        ctx.sendMessage(arrayOf(
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
    fun create(ctx: Context<Player>, name: String, maxPlayers: Int) {
        GameMap(
            name,
            maxPlayers,
            ctx.sender.location
        ).also {
            ctx.sendMessage(it.toString())
            mapManager.add(it.name, it)
        }
    }
}