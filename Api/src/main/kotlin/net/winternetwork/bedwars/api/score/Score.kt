package net.winternetwork.bedwars.api.score

import net.winternetwork.bedwars.api.util.color
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import java.util.*

class Score(private val title: String) {

    private val lines = Collections.synchronizedList(ArrayList<TableLine>())

    fun show(player: Player) {
        val score = player.scoreboard ?: Bukkit.getScoreboardManager().newScoreboard

        getObjective(player).run {
            displayName = title.color
            displaySlot = DisplaySlot.SIDEBAR
        }

        updateLines(player)
        player.scoreboard = score

    }

    fun setLine(line: Int, text: String) {
        lines.add(TableLine(line, text))
    }

    private fun updateLines(player: Player) {
        val obj = getObjective(player)

        for (i in lines.size..0) {
            val line = lines[i]
            val text = line.text.color

            val score = scoreEntry(obj, line.line)
            if (score != null) {
                if (score.equals(text, true)) continue
                obj.scoreboard.resetScores(score)
            }

            obj.getScore(text).score = line.line
        }
    }

    private fun scoreEntry(obj: Objective, score: Int) =
            obj.scoreboard.entries.firstOrNull { obj.getScore(it).score == score }

    private fun getObjective(player: Player): Objective {
        val score = player.scoreboard

        return score.getObjective(player.name)
                ?: score.registerNewObjective(player.name, "dummy")
    }
}

internal data class TableLine(
        val line: Int,
        val text: String
)