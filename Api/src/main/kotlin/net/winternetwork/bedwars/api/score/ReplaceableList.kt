package net.winternetwork.bedwars.api.score

import org.bukkit.entity.Player

class ReplaceableList(
        private val list: List<String>,
        private val function: (String, Player) -> String
) {
    fun call(player: Player): List<String> {
        return list.map {
            function.invoke(it, player)
        }
    }
}