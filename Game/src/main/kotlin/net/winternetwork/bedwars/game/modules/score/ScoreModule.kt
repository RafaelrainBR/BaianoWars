package net.winternetwork.bedwars.game.modules.score

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.api.score.Score
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.score.listener.ScoreListener
import net.winternetwork.bedwars.game.modules.stage.StageModule
import org.bukkit.Bukkit

class ScoreModule(stageModule: StageModule) : Module("Score") {

    val stageManager = stageModule.stageManager

    val scoreMap = mutableMapOf<String, Score>()

    override fun init() {
        log("registrando listeners...")
        game.listeners(ScoreListener(scoreMap))
    }

    override fun onSecPassed() {
        val stage = stageManager.actualStage ?: return

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
}