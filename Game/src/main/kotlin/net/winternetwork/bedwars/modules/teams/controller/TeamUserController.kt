package net.winternetwork.bedwars.modules.teams.controller

import net.winternetwork.bedwars.game.modules.teams.model.Gamer

object TeamUserController {

    private val map = LinkedHashMap<String, Gamer>()

    fun get(id: String) = _root_ide_package_.net.winternetwork.bedwars.modules.teams.controller.TeamUserController.map[id]

    fun add(id: String, gamer: Gamer) = _root_ide_package_.net.winternetwork.bedwars.modules.teams.controller.TeamUserController.map.put(id, gamer)

    fun remove(id: String) = _root_ide_package_.net.winternetwork.bedwars.modules.teams.controller.TeamUserController.map.remove(id)

    fun getAll() = _root_ide_package_.net.winternetwork.bedwars.modules.teams.controller.TeamUserController.map.values
}