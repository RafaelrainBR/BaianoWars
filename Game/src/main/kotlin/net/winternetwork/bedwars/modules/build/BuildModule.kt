package net.winternetwork.bedwars.modules.build

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.metadata.FixedMetadataValue
import java.io.File

class BuildModule : Module("Build") {

    val blockSet = linkedSetOf<Block>()

    override fun init() {
        log("Registrando listeners...")
        game.listeners(BuildListener())
    }

    override fun disable() {
        log("Resetando mapa...")
        blockSet.forEach {
            it.removeMetadata("putted", game)
            it.type = Material.AIR
        }

        File("world/playerdata").run {
            if (exists())
                delete()
        }
    }

    private inner class BuildListener : Listener {

        @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
        )
        fun onBreak(e: BlockBreakEvent) {
            if (e.player.gameMode == GameMode.CREATIVE) return

            if (e.block.hasMetadata("putted"))
                e.isCancelled = true
        }

        @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
        )
        fun onPlace(e: BlockPlaceEvent) {
            if (e.player.gameMode == GameMode.CREATIVE) return

            e.block.run {
                setMetadata("putted", FixedMetadataValue(game, true))
                blockSet.add(this)
            }
        }
    }
}