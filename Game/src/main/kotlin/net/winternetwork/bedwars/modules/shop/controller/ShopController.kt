package net.winternetwork.bedwars.modules.shop.controller

import org.bukkit.entity.Villager

class ShopController {

    private val map = LinkedHashMap<String, Villager>()

    fun get(id: String) = map[id]

    fun add(id: String, entity: Villager) = map.put(id, entity)

    fun remove(id: String) = map.remove(id)

    fun getAll() = map.values
}