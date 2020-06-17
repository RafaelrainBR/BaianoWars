package net.winternetwork.bedwars.core.game.generators;

import net.winternetwork.bedwars.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class GeneratorScheduler {

    private final GeneratorManager manager = GeneratorManager.getInstance();

    public GeneratorScheduler() {
        Bukkit.getScheduler()
                .runTaskTimer(
                        Core.getInstance(),
                        this::schedule,
                        0,
                        20
                );
    }

    private void schedule() {
        for (Generator generator : manager.getAll()) {
            if (generator.getTime() <= 0) {
                Block block = generator.getBlock();

                block.getWorld().dropItem(
                        block.getLocation().add(0, 1, 0),
                        new ItemStack(generator.getItemType())
                );

                generator.setTime(0);
                continue;
            }

            generator.setTime(generator.getTime() - 1);
        }
    }
}
