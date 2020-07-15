package net.winternetwork.bedwars.game.listener

import net.winternetwork.bedwars.game.settings.GameSettings
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener : Listener {

    // TODO: botar import do MapModule

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        e.joinMessage = "§e%sentrou. §f(%d/%d)"
                .format(
                        e.player.displayName,
                        Bukkit.getOnlinePlayers().size,
                        GameSettings.MAX_PLAYERS
                )

        // TODO: Botar pra teleportar pro lobby do mapa
    }
}