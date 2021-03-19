package br.redebaiana.baianowars.modules.score

import br.redebaiana.baianowars.api.module.Module
import br.redebaiana.baianowars.api.score.Score
import br.redebaiana.baianowars.game.game
import br.redebaiana.baianowars.game.settings.GameSettings
import br.redebaiana.baianowars.modules.stage.StageModule
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class ScoreModule(stageModule: StageModule) : Module("Score") {

    private val stageManager = stageModule.stageManager

    val scoreMap = mutableMapOf<String, Score>()

    override fun init() {
        log("registrando listeners...")
        game.listeners(ModuleListener())
    }

    override suspend fun onSecPassed() {
        if (!GameSettings.canStart) return

        val stage = stageManager!!.actualStage

        for (entry in scoreMap) {
            val player = Bukkit.getPlayer(entry.key)

            if (player == null || !player.isOnline) {
                scoreMap.remove(entry.key)
                continue
            }

            val score = entry.value
            val lines = stage.scoreboard.call(player).reversed()

            lines.forEachIndexed { i, s ->
                val id = i - 1
                score.setLine(id, s)
            }

            score.show(player)
        }

    }

    private inner class ModuleListener : Listener {
        @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
        )
        fun onJoin(e: PlayerJoinEvent) {
            scoreMap[e.player.name] = Score("§e§lBAIANO WARS")
        }
    }
}