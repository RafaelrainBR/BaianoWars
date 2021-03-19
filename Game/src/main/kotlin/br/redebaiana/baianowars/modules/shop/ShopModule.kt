package br.redebaiana.baianowars.modules.shop

import br.redebaiana.baianowars.api.module.Module
import br.redebaiana.baianowars.game.game
import br.redebaiana.baianowars.modules.shop.command.ShopCommands
import br.redebaiana.baianowars.modules.shop.controller.ShopController
import br.redebaiana.baianowars.modules.shop.listener.VillagerListener
import br.redebaiana.baianowars.modules.shop.model.menu.MenuView
import me.saiintbrisson.minecraft.InventoryFrame
import org.bukkit.entity.Entity

class ShopModule : Module("Shop") {

    val controller = ShopController()

    val view: MenuView by lazy {
        log("Abrindo hook ao inventory-framework")
        MenuView(game).apply {
            InventoryFrame(game).registerListener()
        }
    }

    override fun init() {
        view
        log("Registrando comandos...")
        game.commands(ShopCommands())
        log("Registrando listeners...")
        game.listeners(VillagerListener())
    }

    override fun disable() {
        controller.getAll().forEach(Entity::remove)
    }

}