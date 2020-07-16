package net.winternetwork.bedwars.game.modules.maps.manager

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.game.modules.maps.model.GameMap
import net.winternetwork.bedwars.game.modules.maps.model.LocationAdapter

class MapManager {

    val maps = mutableMapOf<String, GameMap>()

    fun loadAll(config: YamlConfig) {
        val section = config.getConfigurationSection("maps") ?: return

        val keys = section.getKeys(false)
        if (keys.isEmpty()) return

        for (key in keys) {
            add(GameMap.fromSection(section.getConfigurationSection(key)))
        }
    }

    fun unloadAll(config: YamlConfig) {
        all().forEach {
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

    fun add(map: GameMap) = maps.put(map.name, map)

    fun get(name: String) = maps[name]

    fun all() = ArrayList(maps.values)
}