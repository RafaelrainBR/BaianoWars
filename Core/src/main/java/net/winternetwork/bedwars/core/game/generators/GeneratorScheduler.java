package net.winternetwork.bedwars.core.game.generators;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.Core;
import net.winternetwork.bedwars.core.game.flag.Flags;
import net.winternetwork.bedwars.core.game.stage.StageManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class GeneratorScheduler {

    private final GeneratorManager manager = GeneratorManager.getInstance();

    private final StageManager stageManager = StageManager.getInstance();

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
        final Stage stage = stageManager.getActualStage();

        if (stage.getFlags().contains(Flags.NO_GENERATOR)) return;

        for (Generator generator : manager.getAll()) {
            syncHologram(generator);

            if (generator.getTime() <= 0) {
                Block block = generator.getBlock();

                block.getWorld().dropItem(
                        block.getLocation().add(0, 0.7, 0),
                        new ItemStack(generator.getItemType())
                );

                generator.setTime(10);
                continue;
            }

            generator.setTime(generator.getTime() - 1);
        }
    }

    private void syncHologram(Generator generator) {
        Hologram hologram = generator.getHologram();

        if (hologram == null) {
            hologram = HologramsAPI.createHologram(
                    Core.getInstance(),
                    generator.getBlock()
                            .getLocation()
                            .add(0, 2.0D, 0)
            );

            generator.setHologram(hologram);
        }
        hologram.clearLines();

        hologram.insertItemLine(
                0,
                new ItemStack(generator.getBlock().getType())
        );

        hologram.insertTextLine(
                1,
                String.format("§eTempo: §f%ss", generator.getTime())
        );
    }
}
