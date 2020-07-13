package net.winternetwork.bedwars.api.game.map

import org.bukkit.Location

interface IGameMap {

    val name: String

    val maxPlayers: String

    val lobbyLocation: Location

    val locations: Array<Location>
}