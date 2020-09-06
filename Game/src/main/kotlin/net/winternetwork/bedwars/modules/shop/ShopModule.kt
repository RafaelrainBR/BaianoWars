package net.winternetwork.bedwars.modules.shop

import me.saiintbrisson.minecraft.InventoryFrame
import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import org.bukkit.entity.Entity

class ShopModule : Module("Shop") {

    val controller = _root_ide_package_.net.winternetwork.bedwars.modules.shop.controller.ShopController()

    val view: _root_ide_package_.net.winternetwork.bedwars.modules.shop.model.menu.MenuView by lazy {
        log("Abrindo hook ao inventory-framework")
        _root_ide_package_.net.winternetwork.bedwars.modules.shop.model.menu.MenuView(game).apply {
            InventoryFrame(game).registerListener()
        }
    }

    override fun init() {
        view
        log("Registrando comandos...")
        game.commands(_root_ide_package_.net.winternetwork.bedwars.modules.shop.command.ShopCommands())
        log("Registrando listeners...")
        game.listeners(_root_ide_package_.net.winternetwork.bedwars.modules.shop.listener.VillagerListener())
    }

    override fun disable() {
        controller.getAll().forEach(Entity::remove)
    }

}