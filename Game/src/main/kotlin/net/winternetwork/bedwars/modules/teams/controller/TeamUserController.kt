package net.winternetwork.bedwars.modules.teams.controller

import net.winternetwork.bedwars.modules.teams.model.Gamer

object TeamUserController {

    private val map = LinkedHashMap<String, Gamer>()

    fun get(id: String) = map[id]

    fun add(id: String, gamer: Gamer) = map.put(id, gamer)

    fun remove(id: String) = map.remove(id)

    fun getAll() = map.values
}