package net.winternetwork.bedwars.game.modules.stage.flag;

import net.winternetwork.bedwars.api.game.flag.Flag;
import org.bukkit.GameMode;
import org.bukkit.event.block.BlockBreakEvent;

public class NoBreakFlag implements Flag<BlockBreakEvent> {

    @Override
    public Class<BlockBreakEvent> getEventClass() {
        return BlockBreakEvent.class;
    }

    @Override
    public void execute(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
    }
}
