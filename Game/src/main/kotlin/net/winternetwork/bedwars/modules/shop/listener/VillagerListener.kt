package net.winternetwork.bedwars.modules.shop.listener

import net.winternetwork.bedwars.api.util.inject
import net.winternetwork.bedwars.modules.shop.ShopModule
import net.winternetwork.bedwars.modules.shop.model.menu.MenuView
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEntityEvent

class VillagerListener : Listener {

    private val module: ShopModule by inject()
    private val view: MenuView by lazy { module.view }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun openEvent(e: InventoryOpenEvent) {
        if (e.inventory.type != InventoryType.MERCHANT) return

        e.isCancelled = true
        view.showInventory(e.player as Player)

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun interactEvent(e: PlayerInteractEntityEvent) {
        if (e.rightClicked.type != EntityType.VILLAGER) return

        e.isCancelled = true
        view.showInventory(e.player as Player)
    }
}