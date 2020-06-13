package net.winternetwork.bedwars.core.game.map;

import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.core.game.map.adapter.LocationAdapter;
import net.winternetwork.bedwars.core.game.map.adapter.MapAdapter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapManager {

    private static MapManager instance;

    private final MapAdapter mapAdapter = new MapAdapter();
    private final LocationAdapter locationAdapter = new LocationAdapter();

    private final Map<String, GameMap> MAP_CACHE = new LinkedHashMap<>();

    private MapManager() {
    }

    public static MapManager getInstance() {
        if (instance == null)
            instance = new MapManager();

        return instance;
    }

    public void loadAll(YamlConfig config) {
        ConfigurationSection mapsSection = config.getConfigurationSection("maps");

        if (mapsSection == null) return;

        if (mapsSection.getKeys(false).isEmpty()) return;

        for (String key : mapsSection.getKeys(false)) {
            add(mapAdapter.from(mapsSection.getConfigurationSection(key)));
        }
    }

    public void unloadAll(YamlConfig config) {
        for (GameMap value : getAll()) {
            String prefix = "maps." + value.getName();

            config.set(prefix + ".maxPlayers", value.getMaxPlayers());
            config.set(prefix + ".lobbyLocation", locationAdapter.to(value.getLobbyLocation()));

            config.createSection(prefix + ".locations");
            for (int i = 0; i < value.getSpawnLocations().length; i++) {
                config.set(prefix + ".locations." + (i + 1), locationAdapter.to(value.getSpawnLocations()[i]));
            }
        }

        config.save();
    }

    public void add(GameMap map) {
        MAP_CACHE.put(map.getName(), map);
    }

    public GameMap get(String name) {
        return MAP_CACHE.get(name);
    }

    public List<GameMap> getAll() {
        return new ArrayList<>(MAP_CACHE.values());
    }
}
