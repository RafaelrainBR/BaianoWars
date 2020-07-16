package net.winternetwork.bedwars.api.game.map

import org.bukkit.Location

interface IGameMap {

    val name: String

    val maxPlayers: Int

    val lobbyLocation: Location

    val locations: ArrayList<Location>
}