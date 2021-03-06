package net.winternetwork.bedwars.game.listener

import net.winternetwork.bedwars.api.util.inject
import net.winternetwork.bedwars.game.settings.GameSettings
import net.winternetwork.bedwars.modules.maps.MapModule
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener : Listener {

    private val mapModule: MapModule by inject()

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        e.joinMessage = "§e%s entrou. §f(%d/%d)"
            .format(
                e.player.displayName,
                Bukkit.getOnlinePlayers().size,
                GameSettings.MAX_PLAYERS
            )

        if (GameSettings.canStart) {
            e.player.teleport(mapModule.manager.first.lobbyLocation)
        }
    }
}