package net.winternetwork.bedwars.game.modules.stage.listener

import net.winternetwork.bedwars.api.game.flag.Flag
import net.winternetwork.bedwars.api.util.inject
import net.winternetwork.bedwars.game.modules.stage.StageModule
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerLoginEvent

class FlagListener : Listener {

    val module: StageModule by inject()

    val stageManager = module.stageManager

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        val stage = stageManager.actualStage

        val flags = stage?.flags
        if (flags != null) {
            for (flag in flags) {
                if (flag.eventClass != event.javaClass) continue

                (flag as Flag<EntityDamageByEntityEvent>).execute(event)
            }
        }
    }

    @EventHandler
    fun onJoin(event: PlayerLoginEvent) {
        val stage = stageManager.actualStage

        val flags = stage?.flags
        if (flags != null) {
            for (flag in flags) {
                if (flag.eventClass != event.javaClass) continue

                (flag as Flag<PlayerLoginEvent>).execute(event)
            }
        }
    }

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        val stage = stageManager.actualStage

        val flags = stage?.flags
        if (flags != null) {
            for (flag in flags) {
                if (flag.eventClass != event.javaClass) continue

                (flag as Flag<BlockPlaceEvent>).execute(event)
            }
        }
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val stage = stageManager.actualStage

        val flags = stage?.flags
        if (flags != null) {
            for (flag in flags) {
                if (flag.eventClass != event.javaClass) continue

                (flag as Flag<BlockBreakEvent>).execute(event)
            }
        }
    }
}