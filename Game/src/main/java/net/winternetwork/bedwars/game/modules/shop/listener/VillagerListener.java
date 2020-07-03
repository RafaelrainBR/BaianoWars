package net.winternetwork.bedwars.game.modules.shop.listener;

import lombok.Getter;
import net.winternetwork.bedwars.game.modules.Modules;
import net.winternetwork.bedwars.game.modules.shop.ShopModule;
import net.winternetwork.bedwars.game.modules.shop.model.menu.MenuView;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class VillagerListener implements Listener {

    @Getter(lazy = true)
    private final MenuView view = Modules.getModule(ShopModule.class).getView();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void openEvent(InventoryOpenEvent e) {
        if (e.getInventory().getType() != InventoryType.MERCHANT) return;
        e.setCancelled(true);

        getView().showInventory(((Player) e.getPlayer()));
    }
}
