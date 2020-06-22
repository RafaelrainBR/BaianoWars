package net.winternetwork.bedwars.core.game.generators.adapter;


import net.winternetwork.bedwars.core.game.generators.Generator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class GeneratorAdapter {

    public Generator from(ConfigurationSection section) {

        Generator generator = new Generator(
                section.getName(),
                Material.matchMaterial(section.getString("drop_material"))
        );

        ConfigurationSection blockSection = section.getConfigurationSection("block");
        World world = Bukkit.getWorld(blockSection.getString("world"));

        generator.setBlock(
                world.getBlockAt(
                        blockSection.getInt("x"),
                        blockSection.getInt("y"),
                        blockSection.getInt("z")
                )
        );

        return generator;
    }
}
