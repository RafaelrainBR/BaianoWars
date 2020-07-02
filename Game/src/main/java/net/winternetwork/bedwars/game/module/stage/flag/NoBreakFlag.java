package net.winternetwork.bedwars.game.module.stage.flag;

import net.winternetwork.bedwars.api.game.flag.Flag;
import org.bukkit.event.block.BlockBreakEvent;

public class NoBreakFlag implements Flag<BlockBreakEvent> {

    @Override
    public Class<BlockBreakEvent> getEventClass() {
        return BlockBreakEvent.class;
    }

    @Override
    public void execute(BlockBreakEvent event) {
        if (!event.getPlayer().isOp()) event.setCancelled(true);
    }
}
