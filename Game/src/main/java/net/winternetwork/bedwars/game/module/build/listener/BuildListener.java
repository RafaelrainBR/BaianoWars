package net.winternetwork.bedwars.game.module.build.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.winternetwork.bedwars.game.module.Modules;
import net.winternetwork.bedwars.game.module.build.BuildModule;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

@RequiredArgsConstructor
public class BuildListener implements Listener {

    private final Plugin plugin;

    @Getter(lazy = true)
    private final BuildModule module = Modules.getModule(BuildModule.class);

    @EventHandler(
            priority = EventPriority.HIGH,
            ignoreCancelled = true
    )
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;

        Block block = e.getBlock();

        if (!block.hasMetadata("putted")) {
            e.setCancelled(true);
        }
    }

    @EventHandler(
            priority = EventPriority.HIGH,
            ignoreCancelled = true
    )
    public void onBuild(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();

        block.setMetadata("putted", new FixedMetadataValue(plugin, true));

        getModule().getBlockSet().add(block);
    }

}
