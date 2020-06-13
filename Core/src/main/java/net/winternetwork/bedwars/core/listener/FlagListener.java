package net.winternetwork.bedwars.core.listener;

import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.stage.StageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class FlagListener implements Listener {

    private final StageManager stageManager = StageManager.getInstance();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        final Stage stage = stageManager.getActualStage();

        for (Flag flag : stage.getFlags()) {
            if (flag.getEventClass() != event.getClass()) continue;

            flag.execute(event);
        }
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        final Stage stage = stageManager.getActualStage();

        for (Flag flag : stage.getFlags()) {
            if (flag.getEventClass() != event.getClass()) continue;

            flag.execute(event);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        final Stage stage = stageManager.getActualStage();

        for (Flag flag : stage.getFlags()) {
            if (flag.getEventClass() != BlockPlaceEvent.class) continue;

            flag.execute(e);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        final Stage stage = stageManager.getActualStage();

        for (Flag flag : stage.getFlags()) {
            if (flag.getEventClass() != BlockPlaceEvent.class) continue;

            flag.execute(e);
        }
    }
}
