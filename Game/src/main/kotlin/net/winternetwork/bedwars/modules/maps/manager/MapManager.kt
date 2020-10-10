package net.winternetwork.bedwars.modules.maps.manager

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.module.manager.Manager
import net.winternetwork.bedwars.modules.maps.model.GameMap
import net.winternetwork.bedwars.modules.maps.model.LocationAdapter

class MapManager(
        config: YamlConfig
) : Manager<String, GameMap>(config) {

    override fun loadAll() {
        val section = config.getConfigurationSection("maps") ?: return

        val keys = section.getKeys(false)
        if (keys.isEmpty()) return

        for (key in keys) {
            val map = GameMap.fromSection(section.getConfigurationSection(key))
            add(map.name, map)
        }
    }

    override fun unloadAll() {
        all {
            val prefix = "maps.${it.name}"

            config.set("${prefix}.maxPlayers", it.maxPlayers)
            config.set("${prefix}.lobbyLocation", LocationAdapter.to(it.lobbyLocation))

            config.createSection("${prefix}.locations")
            it.locations.forEachIndexed { i, loc ->
                config.set("${prefix}.locations.${i + 1}", LocationAdapter.to(loc))

            }
        }

        config.save()
    }
}