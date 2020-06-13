package net.winternetwork.bedwars.core.game.map;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.winternetwork.bedwars.api.game.map.IGameMap;
import org.bukkit.Location;

@Getter
@Builder
@ToString
public class GameMap implements IGameMap {

    private final String name;

    private final int maxPlayers;

    private final Location lobbyLocation;

    private final Location[] spawnLocations;

    public void setLocation(int id, Location location) {
        spawnLocations[id - 1] = location;
    }
}
