package net.winternetwork.bedwars.game.modules.generators.object;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

@Getter
@ToString
@RequiredArgsConstructor
public class Generator {

    private final String name;

    private final Material itemType;

    @Setter
    private Block block;

    @Setter
    private int time = 10;

    @Setter
    private Hologram hologram;

    public static Generator fromSection(ConfigurationSection section) {

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
