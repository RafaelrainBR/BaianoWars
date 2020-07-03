package net.winternetwork.bedwars.game.modules.generators;

import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.game.modules.generators.object.Generator;
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

    private final Map<String, Generator> CACHE = new LinkedHashMap<>();

    GeneratorManager() {
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
            put(Generator.fromSection(section.getConfigurationSection(key)));
        }
    }

    public void unloadAll(YamlConfig config) {
        for (Generator value : getAll()) {
            final String prefix = "generators." + value.getName();

            config.set(prefix + ".drop_material", value.getItemType().name());

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
