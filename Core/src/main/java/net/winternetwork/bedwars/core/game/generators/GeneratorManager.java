package net.winternetwork.bedwars.core.game.generators;

import lombok.Getter;
import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.core.game.generators.adapter.GeneratorAdapter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GeneratorManager {

    @Getter(lazy = true)
    private static final GeneratorManager instance = new GeneratorManager();

    private final GeneratorAdapter generatorAdapter = new GeneratorAdapter();

    private final Map<String, Generator> CACHE = new LinkedHashMap<>();

    private GeneratorManager() {
        for (World world : Bukkit.getWorlds()) {
            for (Item item : world.getEntitiesByClass(Item.class)) {
                item.remove();
            }
        }
    }

    public void loadAll(YamlConfig config) {
        ConfigurationSection section = config.getConfigurationSection("generators");

        if (section == null) return;

        if (section.getKeys(false).isEmpty()) return;

        for (String key : section.getKeys(false)) {
            put(generatorAdapter.from(section.getConfigurationSection(key)));
        }
    }

    public void unloadAll(YamlConfig config) {
        for (Generator value : getAll()) {
            final String prefix = "generators." + value.getName();

            config.set(prefix + ".drop_material", value.getBlock().getType().name());

            final Block block = value.getBlock();
            final String blockPrefix = prefix + ".block";

            config.set(blockPrefix + ".world", block.getWorld().getName());
            config.set(blockPrefix + ".x", block.getX());
            config.set(blockPrefix + ".y", block.getY());
            config.set(blockPrefix + ".z", block.getZ());
        }

        config.save();
    }

    public Generator get(String name) {
        return CACHE.get(name);
    }

    public void put(Generator generator) {
        CACHE.put(generator.getName(), generator);
    }

    public void remove(String name) {
        CACHE.remove(name);
    }

    public Set<Generator> getAll() {
        return new LinkedHashSet<>(CACHE.values());
    }
}
