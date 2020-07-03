package net.winternetwork.bedwars.game.modules.stage.flag.listener;

import lombok.RequiredArgsConstructor;
import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.game.modules.stage.StageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;

@RequiredArgsConstructor
public class FlagListener implements Listener {

    private final StageManager stageManager;

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
            if (flag.getEventClass() != BlockBreakEvent.class) continue;

            flag.execute(e);
        }
    }
}
