package net.winternetwork.bedwars.game.modules.stage.flag;

import net.winternetwork.bedwars.api.game.flag.Flag;
import org.bukkit.GameMode;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoBuildFlag implements Flag<BlockPlaceEvent> {

    @Override
    public Class<BlockPlaceEvent> getEventClass() {
        return BlockPlaceEvent.class;
    }

    @Override
    public void execute(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
    }
}
