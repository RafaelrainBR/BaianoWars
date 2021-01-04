package net.winternetwork.bedwars.modules.maps.model

import net.winternetwork.bedwars.api.game.map.IGameMap
import net.winternetwork.bedwars.api.storage.adapter.Adapter
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.ConfigurationSection

data class GameMap(
    override val name: String,
    override val maxPlayers: Int,
    override val lobbyLocation: Location,
    override val locations: ArrayList<Location> = arrayListOf()
) : IGameMap {

    fun setLocation(id: Int, location: Location) {
        locations[id - 1] = location
    }

    companion object {
        fun fromSection(section: ConfigurationSection): GameMap {

            val maxPlayers = section.getInt("maxPlayers")
            val locations = arrayListOf<Location>()

            for (id in section.getConfigurationSection("locations").getKeys(false)) {
                val i = id.toInt()

                locations[i - 1] = LocationAdapter.from(
                    section.getString("locations.$i")
                )
            }

            return GameMap(
                section.name,
                maxPlayers,
                LocationAdapter.from(section.getString("lobbyLocation")),
                locations
            )
        }
    }

}

object LocationAdapter : Adapter<Location, String> {

    override fun from(c: String): Location {
        val split = c.split(";")
        return Location(
            Bukkit.getWorld(split[0]),
            split[1].toDouble(),
            split[2].toDouble(),
            split[3].toDouble()
        )
    }

    override fun to(t: Location): String {
        return ArrayList<String>().apply {
            add(t.world.name)
            add(t.x.toString())
            add(t.y.toString())
            add(t.z.toString())
        }.joinToString(";")
    }
}