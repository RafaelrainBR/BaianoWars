package net.winternetwork.bedwars.core.game.map.adapter;

import net.winternetwork.bedwars.core.game.map.GameMap;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class MapAdapter {

    private final LocationAdapter locationAdapter = new LocationAdapter();

    public GameMap from(ConfigurationSection section) {
        Location[] spawnLocations = new Location[section.getInt("maxPlayers")];

        for (String id : section.getConfigurationSection("locations").getKeys(false)) {
            int i = Integer.parseInt(id);

            spawnLocations[i - 1] = locationAdapter.from(
                    section.getString("locations." + id)
            );
        }

        return GameMap.builder()
                .name(section.getName())
                .maxPlayers(section.getInt("maxPlayers"))
                .lobbyLocation(
                        locationAdapter.from(
                                section.getString("lobbyLocation")
                        )
                )
                .spawnLocations(spawnLocations)
                .build();
    }
}
