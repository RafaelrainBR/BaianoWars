package net.winternetwork.bedwars.modules.stage.flag

import net.winternetwork.bedwars.api.game.flag.Flag
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerLoginEvent

object Flags {

    val NO_BREAK_FLAG = object : Flag<BlockBreakEvent> {
        override val eventClass = BlockBreakEvent::class.java

        override fun execute(e: BlockBreakEvent) {
            if (e.player.gameMode != GameMode.CREATIVE)
                e.isCancelled = true
        }
    }

    val NO_BUILD_FLAG = object : Flag<BlockPlaceEvent> {
        override val eventClass = BlockPlaceEvent::class.java

        override fun execute(e: BlockPlaceEvent) {
            if (e.player.gameMode != GameMode.CREATIVE)
                e.isCancelled = true
        }
    }

    val NO_GENERATOR_FLAG = object : Flag<Event> {
        override val eventClass = Event::class.java

        override fun execute(e: Event) {}
    }

    val NO_JOIN_FLAG = object : Flag<PlayerLoginEvent> {
        override val eventClass = PlayerLoginEvent::class.java

        override fun execute(e: PlayerLoginEvent) {
            e.disallow(
                    PlayerLoginEvent.Result.KICK_WHITELIST,
                    "§cO jogo já está rolando. Aguarde ele acabar."
            )
        }
    }

    val NO_PVP_FLAG = object : Flag<EntityDamageByEntityEvent> {
        override val eventClass = EntityDamageByEntityEvent::class.java

        override fun execute(e: EntityDamageByEntityEvent) {
            if (e.damager is Player && e.entity is Player)
                e.isCancelled = true
        }
    }
}