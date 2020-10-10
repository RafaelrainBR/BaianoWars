package net.winternetwork.bedwars.api.game.stage

import net.winternetwork.bedwars.api.game.flag.Flag
import net.winternetwork.bedwars.api.score.ReplaceableList
import org.bukkit.Bukkit
import org.bukkit.entity.Player

abstract class Stage(
        val name: String,
        val time: Int
) {

    var timeLeft = time
        protected set

    abstract val flags: List<Flag<*>>
    abstract val scoreboard: ReplaceableList

    open fun onStageJoin() {}
    open fun onSecondPassed() {}
    open fun onTimeLeft() {}
    open fun onStageExit() {}

    fun operateTime() {
        timeLeft -= 1
    }

    fun resetTime() {
        timeLeft = time
    }

    protected fun broadcast(vararg messages: String) {
        messages.forEach {
            Bukkit.broadcastMessage(it)
        }
    }

    val onlinePlayers: Int
        get() = Bukkit.getOnlinePlayers().size

    val allPlayers: List<Player>
        get() = ArrayList(Bukkit.getOnlinePlayers())
}