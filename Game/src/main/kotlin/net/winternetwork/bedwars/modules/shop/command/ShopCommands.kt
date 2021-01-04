package net.winternetwork.bedwars.modules.shop.command

import me.saiintbrisson.minecraft.command.annotation.Command
import me.saiintbrisson.minecraft.command.command.Context
import me.saiintbrisson.minecraft.command.target.CommandTarget
import net.winternetwork.bedwars.api.util.inject
import net.winternetwork.bedwars.modules.shop.ShopModule
import net.winternetwork.bedwars.modules.shop.controller.ShopController
import net.winternetwork.bedwars.modules.shop.model.VillagerModel
import net.winternetwork.bedwars.modules.shop.model.menu.MenuView
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