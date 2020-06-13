package net.winternetwork.bedwars.core.game.flag;

import net.winternetwork.bedwars.api.game.flag.Flag;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class NoPvpFlag implements Flag<EntityDamageByEntityEvent> {

    @Override
    public Class<EntityDamageByEntityEvent> getEventClass() {
        return EntityDamageByEntityEvent.class;
    }

    @Override
    public void execute(EntityDamageByEntityEvent event) {
        if (
                event.getDamager() instanceof Player
                        || event.getEntity() instanceof Player
        ) event.setCancelled(true);
    }
}
