package br.redebaiana.baianowars.api.score

import org.bukkit.entity.Player

class ReplaceableList(
    private val list: List<String>,
    private val replaces: (Player) -> Map<String, String>
) {
    fun call(player: Player): List<String> {
        return list.map { text ->
            text.apply {
                replaces(player).forEach { (key, value) ->
                    this.replace(key, value)
                }
            }
        }
    }
}