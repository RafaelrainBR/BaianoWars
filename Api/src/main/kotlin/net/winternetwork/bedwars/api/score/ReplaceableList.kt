package net.winternetwork.bedwars.api.score

import org.bukkit.entity.Player

class ReplaceableList(
        val list: List<String>,
        val function: (String, Player) -> String
) {
    fun call(player: Player): List<String> {
        return list.map {
            function.invoke(it, player)
        }
    }
}