package br.redebaiana.baianowars.modules.shop.command

import br.redebaiana.baianowars.api.util.inject
import br.redebaiana.baianowars.modules.shop.ShopModule
import br.redebaiana.baianowars.modules.shop.controller.ShopController
import br.redebaiana.baianowars.modules.shop.model.VillagerModel
import br.redebaiana.baianowars.modules.shop.model.menu.MenuView
import me.saiintbrisson.minecraft.command.annotation.Command
import me.saiintbrisson.minecraft.command.command.Context
import me.saiintbrisson.minecraft.command.target.CommandTarget
import org.bukkit.entity.Player

class ShopCommands {

    val module: ShopModule by inject()

    private val controller: ShopController by lazy { module.controller }
    private val view: MenuView by lazy { module.view }

    @Command(
        name = "summonshop",
        permission = "summonshop.use",
        target = CommandTarget.PLAYER
    )
    fun summon(ctx: Context<Player>, id: String) {
        val location = ctx.sender.location

        controller.add(
            id,
            VillagerModel.spawnVillager(location)
        )
    }

    @Command(name = "openshop", permission = "openshop.use")
    fun openShop(ctx: Context<Player>) {
        view.showInventory(ctx.sender)
    }
}