package net.winternetwork.bedwars.game.modules.shop

import me.saiintbrisson.minecraft.InventoryFrame
import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.shop.command.ShopCommands
import net.winternetwork.bedwars.game.modules.shop.listener.VillagerListener
import net.winternetwork.bedwars.game.modules.shop.model.menu.MenuView
import org.bukkit.entity.Entity

class ShopModule : Module("Shop") {

    lateinit var view: MenuView

    val controller = ShopController()

    override fun init() {
        log("Abrindo hook ao inventory-framework")
        view = MenuView(game)
        InventoryFrame(game).registerListener()

        log("Registrando comandos...")
        game.commands(ShopCommands())
        log("Registrando listeners...")
        game.listeners(VillagerListener())
    }

    override fun disable() {
        controller.getAll().forEach(Entity::remove)
    }

}