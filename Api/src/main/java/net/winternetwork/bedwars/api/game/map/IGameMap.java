package net.winternetwork.bedwars.api.game.map;

import org.bukkit.Location;

public interface IGameMap {

    String getName();

    int getMaxPlayers();

    Location getLobbyLocation();

    Location[] getSpawnLocations();

}
