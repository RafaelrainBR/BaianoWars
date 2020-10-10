package net.winternetwork.bedwars.modules.stage.listener

import net.winternetwork.bedwars.api.game.flag.Flag
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerLoginEvent

class FlagListener(stageModule: _root_ide_package_.net.winternetwork.bedwars.modules.stage.StageModule) : Listener {

    private val stageManager = stageModule.stageManager

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        val stage = stageManager?.actualStage ?: return

        val flags = stage.flags
        for (flag in flags) {
            if (flag.eventClass != event.javaClass) continue

            (flag as Flag<EntityDamageByEntityEvent>).execute(event)
        }
    }

    @EventHandler
    fun onJoin(event: PlayerLoginEvent) {
        val stage = stageManager?.actualStage ?: return

        val flags = stage.flags
        for (flag in flags) {
            if (flag.eventClass != event.javaClass) continue

            (flag as Flag<PlayerLoginEvent>).execute(event)
        }
    }

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        val stage = stageManager?.actualStage ?: return

        val flags = stage.flags
        for (flag in flags) {
            if (flag.eventClass != event.javaClass) continue

            (flag as Flag<BlockPlaceEvent>).execute(event)
        }
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val stage = stageManager?.actualStage ?: return

        val flags = stage.flags
        for (flag in flags) {
            if (flag.eventClass != event.javaClass) continue

            (flag as Flag<BlockBreakEvent>).execute(event)
        }
    }
}