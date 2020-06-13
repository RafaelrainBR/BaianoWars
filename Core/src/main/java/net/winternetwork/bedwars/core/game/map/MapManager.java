package net.winternetwork.bedwars.core.game.map;

import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.core.game.map.adapter.MapAdapter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapManager {

    private static MapManager instance;
    private final MapAdapter mapAdapter = new MapAdapter();
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
        ConfigurationSection maps = config.getConfigurationSection("maps");

        for (GameMap value : getAll()) {
            ConfigurationSection section = config.getConfigurationSection("maps." + value.getName());

            mapAdapter.applyTo(value, section);
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
