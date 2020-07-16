package net.winternetwork.bedwars.game.modules.score.listener

import net.winternetwork.bedwars.api.score.Score
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class ScoreListener(
        val map: MutableMap<String, Score>
) : Listener {

    @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
    )
    fun onJoin(e: PlayerJoinEvent) {
        val name = e.player.name

        map[name] = Score("§e§lBAIANO WARS")
    }
}