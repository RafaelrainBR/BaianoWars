package net.winternetwork.bedwars.game.module.map.object;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.winternetwork.bedwars.api.game.map.IGameMap;
import net.winternetwork.bedwars.game.module.map.object.adapter.LocationAdapter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

@Getter
@Builder
@ToString
public class GameMap implements IGameMap {

    private final String name;

    private final int maxPlayers;

    private final Location lobbyLocation;

    private final Location[] spawnLocations;

    public static GameMap fromSection(ConfigurationSection section) {
        LocationAdapter locationAdapter = new LocationAdapter();

        Location[] spawnLocations = new Location[section.getInt("maxPlayers")];

        for (String id : section.getConfigurationSection("locations").getKeys(false)) {
            int i = Integer.parseInt(id);

            spawnLocations[i - 1] = locationAdapter.from(
                    section.getString("locations." + i)
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

    public void setLocation(int id, Location location) {
        spawnLocations[id - 1] = location;
    }
}
